package com.FarmaPet.FarmaPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FarmaPet.FarmaPet.model.ModelAnimal;

public interface AnimalRepository extends JpaRepository<ModelAnimal, Integer> {}
