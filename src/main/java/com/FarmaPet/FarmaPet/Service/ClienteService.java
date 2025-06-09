package com.FarmaPet.FarmaPet.Service;

import com.FarmaPet.FarmaPet.Model.ModelCliente;
import com.FarmaPet.FarmaPet.Repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}
