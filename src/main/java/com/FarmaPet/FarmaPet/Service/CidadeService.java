package com.FarmaPet.FarmaPet.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FarmaPet.FarmaPet.Model.Endereco.ModelCidade;
import com.FarmaPet.FarmaPet.Repository.CidadeRepository;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<ModelCidade> listarTodas() {
        return cidadeRepository.findAll();
    }

    public Optional<ModelCidade> buscarPorId(int id) {
        return cidadeRepository.findById(id);
    }

    public ModelCidade salvar(ModelCidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public void deletar(int id) {
        cidadeRepository.deleteById(id);
    }

    public ModelCidade atualizar(int id, ModelCidade cidadeAtualizada) {
        return cidadeRepository.findById(id).map(cidade -> {
            cidade.setDescricao(cidadeAtualizada.getDescricao());
            cidade.setUf(cidadeAtualizada.getUf());
            return cidadeRepository.save(cidade);
        }).orElseGet(() -> {
            cidadeAtualizada.setIdCidade(id);
            return cidadeRepository.save(cidadeAtualizada);
        });
    }
}
