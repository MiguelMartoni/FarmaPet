package com.FarmaPet.FarmaPet.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmaPet.FarmaPet.Model.Endereço.ModelBairro;
import com.FarmaPet.FarmaPet.Repository.BairroRepository;

@Service
public class BairroService {

    private final BairroRepository bairroRepository;

    public BairroService(BairroRepository bairroRepository) {
        this.bairroRepository = bairroRepository;
    }

    public ModelBairro salvarBairro(ModelBairro bairro) {
        return bairroRepository.save(bairro);
    }

    public List<ModelBairro> listarTodos() {
        return bairroRepository.findAll();
    }

    public Optional<ModelBairro> buscarPorId(int id) {
        return bairroRepository.findById(id);
    }

    public void deletarPorId(int id) {
        bairroRepository.deleteById(id);
    }
}
