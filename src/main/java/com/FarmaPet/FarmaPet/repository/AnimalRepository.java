package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.ModelAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<ModelAnimal, Integer> {}
