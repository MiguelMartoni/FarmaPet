package com.FarmaPet.FarmaPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FarmaPet.FarmaPet.model.Endereco.ModelUf;

public interface UfRepository extends JpaRepository<ModelUf, Integer> {
}
