package com.FarmaPet.FarmaPet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.ModelCliente;
import com.FarmaPet.FarmaPet.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<ModelCliente> findAll() {
        return repo.findAll();
    }

    public Optional<ModelCliente> findById(Integer id) {
        return repo.findById(id);
    }

    @Transactional
    public ModelCliente save(ModelCliente cliente) {
        return repo.save(cliente);
    }

    @Transactional
    public ModelCliente update(ModelCliente cliente) {
        return repo.save(cliente);
    }

    @Transactional
    public void delete(ModelCliente cliente) {
        repo.delete(cliente);
    }

    public List<ModelCliente> findByEndereco(ModelEndereco endereco) {
        return repo.findByEndereco(endereco);
    }

    public List<String> listarCidadesComClientes() {
        return repo.buscarNomesDasCidadesComClientes();
    }
}
