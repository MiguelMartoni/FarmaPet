package com.FarmaPet.FarmaPet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FarmaPet.FarmaPet.model.ModelMedicamento;

public interface RepositoryMedicamento extends 
JpaRepository<ModelMedicamento, Integer>{
    List<ModelMedicamento> findByQuantidadeEstoqueGreaterThan(int quantidade);
    
}