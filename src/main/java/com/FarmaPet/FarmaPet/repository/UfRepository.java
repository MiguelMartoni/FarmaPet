package com.FarmaPet.FarmaPet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FarmaPet.FarmaPet.model.endereco.ModelUf;

public interface UfRepository extends JpaRepository<ModelUf, Integer> {
    Optional<ModelUf> findBySiglaIgnoreCase(String sigla);
}
