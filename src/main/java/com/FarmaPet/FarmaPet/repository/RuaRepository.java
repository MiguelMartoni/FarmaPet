package com.FarmaPet.FarmaPet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FarmaPet.FarmaPet.model.endereco.ModelRua;

@Repository
public interface RuaRepository extends JpaRepository<ModelRua, Integer> {
    Optional<ModelRua> findByDescricaoContainingIgnoreCase(String descricao);
}
