package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository  extends JpaRepository<ModelFuncionario, Long> {
}
