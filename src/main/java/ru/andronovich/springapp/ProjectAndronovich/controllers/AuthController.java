package ru.andronovich.springapp.ProjectAndronovich.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.andronovich.springapp.ProjectAndronovich.models.Person;
import ru.andronovich.springapp.ProjectAndronovich.repositories.PeopleRepository;
import ru.andronovich.springapp.ProjectAndronovich.services.RegistrationService;

@Controller
@RequestMapping("/authority")
public class AuthController {

    private final RegistrationService registrationService;
    private final PeopleRepository peopleRepository;

    public AuthController(RegistrationService registrationService, PeopleRepository peopleRepository) {
        this.registrationService = registrationService;
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("")
    public String loginPage(@ModelAttribute ("person") Person person){
        return "auth/authority";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") Person person){

        if(peopleRepository.findByUsername(person.getUsername()).isEmpty()) {
            registrationService.register(person);
            return "redirect:/process_login";
        }
        return "redirect:/authority?errorr";
    }


}
