package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.endereco.ModelRua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuaRepository extends JpaRepository<ModelRua, Integer> {
}
