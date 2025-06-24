package com.FarmaPet.FarmaPet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import com.FarmaPet.FarmaPet.repository.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<ModelFuncionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<ModelFuncionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    @Transactional
    public ModelFuncionario save(ModelFuncionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void delete(ModelFuncionario funcionario) {
        funcionarioRepository.delete(funcionario);
    }

    public List<ModelFuncionario> findByEndereco(ModelEndereco endereco) {
        return funcionarioRepository.findByEndereco(endereco);
    }
}