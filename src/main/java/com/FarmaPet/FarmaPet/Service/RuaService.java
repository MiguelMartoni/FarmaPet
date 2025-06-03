package com.FarmaPet.FarmaPet.Service;

import com.FarmaPet.FarmaPet.Model.Endereco.ModelRua;
import com.FarmaPet.FarmaPet.Repository.RuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuaService {

    @Autowired
    private RuaRepository ruaRepository;

    public ModelRua saveRua(ModelRua rua) {
        return ruaRepository.save(rua);
    }

    public List<ModelRua> listAll() {
        return ruaRepository.findAll();
    }

    public Optional<ModelRua> findById(int id) {
        return ruaRepository.findById(id);
    }

    public void deleteRua(int id) {
        ruaRepository.deleteById(id);
    }
}
