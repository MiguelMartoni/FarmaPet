package com.FarmaPet.FarmaPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FarmaPet.FarmaPet.model.endereco.ModelBairro;

public interface BairroRepository extends JpaRepository<ModelBairro, Integer> {
}
