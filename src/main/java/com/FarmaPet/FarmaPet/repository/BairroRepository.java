package com.FarmaPet.FarmaPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FarmaPet.FarmaPet.model.Endereco.ModelBairro;

public interface BairroRepository extends JpaRepository<ModelBairro, Integer> {
}
