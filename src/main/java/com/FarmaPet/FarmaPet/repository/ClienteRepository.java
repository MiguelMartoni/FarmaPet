package com.FarmaPet.FarmaPet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.FarmaPet.FarmaPet.dtos.CidadeQuantidadeDTO;
import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.ModelCliente;

public interface ClienteRepository extends JpaRepository<ModelCliente, Integer> {

    List<ModelCliente> findByEndereco(ModelEndereco endereco);

    @Query("SELECT DISTINCT CONCAT(c.endereco.cidade.descricao, '/', c.endereco.cidade.uf.sigla) FROM ModelCliente c")
    List<String> buscarNomesDasCidadesComClientes();

    @Query("SELECT new com.FarmaPet.FarmaPet.dtos.CidadeQuantidadeDTO(" +
           "CONCAT(c.endereco.cidade.descricao, '/', c.endereco.cidade.uf.sigla), COUNT(c)) " +
           "FROM ModelCliente c GROUP BY c.endereco.cidade.descricao, c.endereco.cidade.uf.sigla")
    List<CidadeQuantidadeDTO> buscarQuantidadePorCidade();
}
