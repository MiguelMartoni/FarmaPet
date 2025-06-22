package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.ModelCliente;
import com.FarmaPet.FarmaPet.model.endereco.ModelEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ModelCliente, Integer> {
    List<ModelCliente> findByEndereco(ModelEndereco endereco);
}
