package com.FarmaPet.FarmaPet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FarmaPet.FarmaPet.Model.Endereço.ModelCidade;

public interface CidadeRepository extends JpaRepository<ModelCidade, Integer> {
}
