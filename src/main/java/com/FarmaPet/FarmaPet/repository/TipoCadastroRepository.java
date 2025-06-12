package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.ModelTipoCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCadastroRepository extends JpaRepository<ModelTipoCadastro, Long> {
}
