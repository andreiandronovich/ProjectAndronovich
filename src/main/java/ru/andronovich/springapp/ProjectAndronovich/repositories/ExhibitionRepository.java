package ru.andronovich.springapp.ProjectAndronovich.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andronovich.springapp.ProjectAndronovich.models.Exhibition;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {


}
