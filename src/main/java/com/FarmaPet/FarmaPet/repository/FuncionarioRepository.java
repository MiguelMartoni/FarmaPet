package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository  extends JpaRepository<ModelFuncionario, Long> {
    Optional<ModelFuncionario> findByNomeUsuario(String nomeUsuario);
}
