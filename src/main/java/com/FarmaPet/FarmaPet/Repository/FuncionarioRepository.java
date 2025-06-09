package com.FarmaPet.FarmaPet.Repository;

import com.FarmaPet.FarmaPet.Model.ModelFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository  extends JpaRepository<ModelFuncionario, Long> {
    Optional<ModelFuncionario> findByNomeUsuario(String nomeUsuario);
}
