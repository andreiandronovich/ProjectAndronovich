package ru.andronovich.springapp.ProjectAndronovich.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.andronovich.springapp.ProjectAndronovich.models.Exhibition;
import ru.andronovich.springapp.ProjectAndronovich.models.Person;
import ru.andronovich.springapp.ProjectAndronovich.security.PersonDetails;
import ru.andronovich.springapp.ProjectAndronovich.services.AdminService;
import ru.andronovich.springapp.ProjectAndronovich.services.ExhibitionService;
import ru.andronovich.springapp.ProjectAndronovich.services.PersonService;

import java.util.*;


@Controller
@RequestMapping("/exhibition")
public class MainController {

    private final ExhibitionService exhibitionService;
    private final AdminService adminService;
    private final PersonService personService;

    @Autowired
    public MainController(ExhibitionService exhibitionService, AdminService adminService, PersonService personService) {
        this.exhibitionService = exhibitionService;
        this.adminService = adminService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("exhibitions", exhibitionService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("exhibition", exhibitionService.findOne(id));
        return "show";
    }
//------------------------------------- FAVORITE --------------------------------------------
    @GetMapping("/favorites")
    public String favorites(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("favorites", personService.getFavorite(personDetails.getPerson()));
        return "favorites";
    }

    @PatchMapping ("/favorite/{id}")
    public String create(@ModelAttribute("exhibition") Exhibition exhibition,@PathVariable("id") int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Exhibition updateExhibition = exhibitionService.findOne(id);

        if(personDetails.getPerson().getExhibitions().contains(updateExhibition)){
            return "redirect:/exhibition";
        }
        if(personDetails.getPerson().getExhibitions().isEmpty()){
            personDetails.getPerson().setExhibitions(new ArrayList<>(Collections.singletonList(updateExhibition)));
        }else {
        personDetails.getPerson().getExhibitions().add(updateExhibition);
        }
        personService.addFavorite(personDetails.getPerson());
        // добавление в список любимых товаров
        return "redirect:/exhibition";
    }

    @DeleteMapping("/favorite/{id}")
    public String deleteFromFavorite(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Exhibition ex = exhibitionService.findOne(id);
        personService.delete(personDetails.getPerson(), ex);
        return "redirect:/exhibition/favorites";
    }

    //====================================================================================
    @GetMapping("/admin")
    public String adminPage(){
        adminService.doAdminStaff();
        return "/adminPage";
    }
//------------------------------- USER PROFILE -------------------------------------------------------
    @GetMapping("/profile")
    public String profile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("person", personService.findOne(personDetails.getId()));
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("person", personService.findOne(personDetails.getId()));
        return "editProfile";
    }

    @PatchMapping("/profile/edit")
    public String update(@ModelAttribute("person")  Person person)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        int id = personDetails.getId();
        personService.update(id,person,personDetails.getPassword());
        return "redirect:/exhibition/profile";
    }

    @DeleteMapping("/person/{id}")
    public String deleteUser(@PathVariable("id") int id){
        personService.deleteUser(id);
        return "redirect:/authority";
    }
                // for admin. another redirect
    @DeleteMapping("/user/{id}")
    public String deleteUsers(@PathVariable("id") int id){
        personService.deleteUser(id);
        return "redirect:/exhibition/users";
    }

//---------------------------- ADMIN CREATE AND DELETE EXHIBITION ------------------------------------------------------
    @GetMapping("/create")
    public String createExhibition(@ModelAttribute("exhibition") Exhibition exhibition){
        return "createExhibition";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("exhibition") Exhibition exhibition) {
    exhibitionService.save(exhibition);
        return "redirect:/exhibition";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") int id){
        exhibitionService.delete(id);
        return "redirect:/exhibition";
    }

    @GetMapping("/{id}/edit")
    public String editExhibition(Model model, @PathVariable("id") int id){
        model.addAttribute("exhibition", exhibitionService.findOne(id));
        return "editExhibition";
    }

    @PatchMapping("/{id}")
    public String updateExhibition(@ModelAttribute("exhibition")  Exhibition exhibition,
                                   @PathVariable("id") int id){
        exhibitionService.update(id, exhibition);
        return "redirect:/exhibition";
    }

//============================================================================================================

    @GetMapping("/users")
    public String user(Model model){
        model.addAttribute("people", personService.findAll());
        return "USERS";
    }
}
