package ru.andronovich.springapp.ProjectAndronovich.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andronovich.springapp.ProjectAndronovich.models.Exhibition;
import ru.andronovich.springapp.ProjectAndronovich.models.Person;
import ru.andronovich.springapp.ProjectAndronovich.repositories.ExhibitionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;

    @Autowired
    public ExhibitionService(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    public List<Exhibition> findAll(){
        return exhibitionRepository.findAll();
    }

    public Exhibition findOne(int id){
        Optional<Exhibition> foundExhibition = exhibitionRepository.findById(id);
        return foundExhibition.orElse(null);
    }

    @Transactional
    public void save(Exhibition exhibition){
        exhibitionRepository.save(exhibition);
    }

    @Transactional
    public void delete(int id){
        exhibitionRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Exhibition updateExhibition){
        updateExhibition.setId(id);
        exhibitionRepository.save(updateExhibition);
    }
}
