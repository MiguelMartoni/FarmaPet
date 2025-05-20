package com.FarmaPet.FarmaPet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FarmaPet.FarmaPet.Model.ModelAnimal;

public interface AnimalRepository extends JpaRepository<ModelAnimal, Integer> {}
