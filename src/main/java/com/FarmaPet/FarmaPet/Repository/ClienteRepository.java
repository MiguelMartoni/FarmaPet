package com.FarmaPet.FarmaPet.Repository;

import com.FarmaPet.FarmaPet.Model.ModelCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ModelCliente, Integer> {
}
