package com.FarmaPet.FarmaPet.Repository;

import com.FarmaPet.FarmaPet.Model.Endereço.ModelEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<ModelEndereco, Long> {
}
