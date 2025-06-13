package com.FarmaPet.FarmaPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FarmaPet.FarmaPet.model.Endereco.ModelCidade;

public interface CidadeRepository extends JpaRepository<ModelCidade, Integer> {
}
