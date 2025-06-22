package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import com.FarmaPet.FarmaPet.model.endereco.ModelEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository  extends JpaRepository<ModelFuncionario, Long> {
    Optional<ModelFuncionario> findByNomeUsuario(String nomeUsuario);

    List<ModelFuncionario> findByEndereco(ModelEndereco endereco);
}
