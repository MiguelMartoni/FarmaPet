package com.FarmaPet.FarmaPet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FarmaPet.FarmaPet.Model.Endereço.ModelBairro;

public interface BairroRepository extends JpaRepository<ModelBairro, Integer> {
}
