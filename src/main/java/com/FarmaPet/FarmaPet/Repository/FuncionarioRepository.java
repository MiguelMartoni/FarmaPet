package com.FarmaPet.FarmaPet.Repository;

import com.FarmaPet.FarmaPet.Model.ModelFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository  extends JpaRepository<ModelFuncionario, Long> {
}
