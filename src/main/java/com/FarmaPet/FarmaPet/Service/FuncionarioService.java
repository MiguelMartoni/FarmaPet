package com.FarmaPet.FarmaPet.Service;

import com.FarmaPet.FarmaPet.Model.ModelFuncionario;
import com.FarmaPet.FarmaPet.Repository.FuncionarioRepository;
import com.FarmaPet.FarmaPet.Repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
