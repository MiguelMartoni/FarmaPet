package com.FarmaPet.FarmaPet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FarmaPet.FarmaPet.Model.Endereco.ModelUf;

public interface UfRepository extends JpaRepository<ModelUf, Integer> {
}
