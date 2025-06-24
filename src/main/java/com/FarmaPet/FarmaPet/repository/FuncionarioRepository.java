package com.FarmaPet.FarmaPet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.ModelFuncionario;

public interface FuncionarioRepository  extends JpaRepository<ModelFuncionario, Long> {
    Optional<ModelFuncionario> findByNomeUsuario(String nomeUsuario);

    List<ModelFuncionario> findByEndereco(ModelEndereco endereco);
}