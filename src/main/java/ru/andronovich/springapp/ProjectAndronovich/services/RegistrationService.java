package ru.andronovich.springapp.ProjectAndronovich.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andronovich.springapp.ProjectAndronovich.models.Person;
import ru.andronovich.springapp.ProjectAndronovich.repositories.PeopleRepository;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person){

        if(peopleRepository.findByUsername(person.getUsername()).isEmpty()){
            person.setRole("ROLE_USER");
            person.setGender(" пол не указан");
            person.setAddress("адрес не указан");
            person.setDate(" год не указан");
            peopleRepository.save(person);
        }else {

        }
    }
}
