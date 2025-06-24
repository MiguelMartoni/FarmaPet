package com.FarmaPet.FarmaPet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.ModelCliente;

public interface ClienteRepository extends JpaRepository<ModelCliente, Integer> {
    List<ModelCliente> findByEndereco(ModelEndereco endereco);
}