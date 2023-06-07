package ru.andronovich.springapp.ProjectAndronovich.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andronovich.springapp.ProjectAndronovich.models.Exhibition;
import ru.andronovich.springapp.ProjectAndronovich.models.Person;

import java.util.List;
import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);
}
