package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<ModelEndereco, Long> {
}
