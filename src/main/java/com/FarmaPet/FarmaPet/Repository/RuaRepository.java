package com.FarmaPet.FarmaPet.Repository;

import com.FarmaPet.FarmaPet.Model.Endereco.ModelRua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuaRepository extends JpaRepository<ModelRua, Integer> {
}
