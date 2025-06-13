package com.FarmaPet.FarmaPet.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.FarmaPet.FarmaPet.model.ModelAnimal;
import com.FarmaPet.FarmaPet.repository.AnimalRepository;

@Service
public class AnimalService {

    private final AnimalRepository repo;

    public AnimalService(AnimalRepository repo) {
        this.repo = repo;
    }

    /* READ ALL */
    public List<ModelAnimal> findAll() {
        return repo.findAll();
    }

    /* READ ONE */
    public Optional<ModelAnimal> findById(Integer id) {
        return repo.findById(id);
    }

    /* CREATE */
    @Transactional
    public ModelAnimal save(ModelAnimal animal) {
        return repo.save(animal);
    }

    /* UPDATE (id já existe) */
    @Transactional
    public ModelAnimal update(ModelAnimal animal) {
        return repo.save(animal);
    }

    /* DELETE */
    @Transactional
    public void delete(ModelAnimal animal) {
        repo.delete(animal);
    }
}