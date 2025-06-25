package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.Endereco.ModelRua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuaRepository extends JpaRepository<ModelRua, Integer> {
    Optional<ModelRua> findByDescricaoContainingIgnoreCase(String descricao);
}