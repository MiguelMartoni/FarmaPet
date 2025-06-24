package com.FarmaPet.FarmaPet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FarmaPet.FarmaPet.model.endereco.ModelUf;
import com.FarmaPet.FarmaPet.repository.UfRepository;

@Service
public class UfService {

    private final UfRepository ufRepository;

    public UfService(UfRepository ufRepository) {
        this.ufRepository = ufRepository;
    }

    public List<ModelUf> listarTodos() {
        return ufRepository.findAll();
    }

    public Optional<ModelUf> buscarPorId(int id) {
        return ufRepository.findById(id);
    }

    public ModelUf salvarUf(ModelUf uf) {
        return ufRepository.save(uf);
    }

    public void deletarUf(int id) {
        ufRepository.deleteById(id);
    }

    public ModelUf atualizarUf(int id, ModelUf ufAtualizada) {
        return ufRepository.findById(id).map(uf -> {
            uf.setSigla(ufAtualizada.getSigla());
            uf.setDescricao(ufAtualizada.getDescricao()); // corrigido aqui
            return ufRepository.save(uf);
        }).orElseThrow(() -> new RuntimeException("UF não encontrada"));
    }

    public Optional<ModelUf> buscarPorSigla(String sigla) {
        return ufRepository.findBySiglaIgnoreCase(sigla);
    }

}
