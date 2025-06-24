package com.FarmaPet.FarmaPet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FarmaPet.FarmaPet.model.ModelMedicamento;
import com.FarmaPet.FarmaPet.repository.RepositoryMedicamento;

import jakarta.transaction.Transactional;

@Service
public class MedicamentoService {

    @Autowired
    private RepositoryMedicamento repository;

    /* --------- READ ALL --------- */
    public List<ModelMedicamento> findAll() {
        return repository.findAll();
    }

    /* --------- READ BY ID --------- */
    public Optional<ModelMedicamento> findById(int id) {
        return repository.findById(id);
    }

    /* --------- CREATE / SAVE --------- */
    @Transactional
    public ModelMedicamento save(ModelMedicamento medicamento) {
        return repository.save(medicamento);
    }

    /* --------- UPDATE --------- */
    @Transactional
    public ModelMedicamento update(ModelMedicamento medicamento) {
        // save() cobre tanto insert quanto update quando o ID já existe
        return repository.save(medicamento);
    }

    /* --------- DELETE --------- */
    @Transactional
    public void delete(ModelMedicamento medicamento) {
        repository.delete(medicamento);
    }

    public List<ModelMedicamento> findByQuantidadeEstoqueGreaterThan(int quantidade) {
        return repository.findByQuantidadeEstoqueGreaterThan(quantidade);
    }
}