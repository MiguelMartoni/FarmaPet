package com.FarmaPet.FarmaPet.service;

import com.FarmaPet.FarmaPet.model.Endereco.ModelRua;
import com.FarmaPet.FarmaPet.repository.RuaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuaService {

    private final RuaRepository ruaRepository;

    public RuaService(RuaRepository ruaRepository) {
        this.ruaRepository = ruaRepository;
    }

    public ModelRua saveRua(ModelRua rua) {
        return ruaRepository.save(rua);
    }

    public List<ModelRua> listAll() {
        return ruaRepository.findAll();
    }

    public Optional<ModelRua> findById(int id) {
        return ruaRepository.findById(id);
    }

    public Optional<ModelRua> findByDescricao(String descricao) {
        return ruaRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public void deleteRua(int id) {
        ruaRepository.deleteById(id);
    }
}