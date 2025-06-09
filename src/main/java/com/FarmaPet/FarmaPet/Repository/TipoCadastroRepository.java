package com.FarmaPet.FarmaPet.Repository;

import com.FarmaPet.FarmaPet.Model.ModelTipoCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCadastroRepository extends JpaRepository<ModelTipoCadastro, Long> {
}
