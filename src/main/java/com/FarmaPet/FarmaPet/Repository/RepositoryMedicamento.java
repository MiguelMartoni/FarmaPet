package com.FarmaPet.FarmaPet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FarmaPet.FarmaPet.Model.ModelMedicamento;

public interface RepositoryMedicamento extends 
JpaRepository<ModelMedicamento, Integer>{
    
}
