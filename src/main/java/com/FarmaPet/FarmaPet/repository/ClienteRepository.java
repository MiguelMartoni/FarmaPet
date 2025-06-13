package com.FarmaPet.FarmaPet.repository;

import com.FarmaPet.FarmaPet.model.ModelCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ModelCliente, Integer> {
}
