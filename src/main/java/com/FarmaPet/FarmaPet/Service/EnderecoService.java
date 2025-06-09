package com.FarmaPet.FarmaPet.Service;

import com.FarmaPet.FarmaPet.dtos.DtoEndereco;
import com.FarmaPet.FarmaPet.Model.Endereco.ModelBairro;
import com.FarmaPet.FarmaPet.Model.Endereco.ModelCidade;
import com.FarmaPet.FarmaPet.Model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.Model.Endereco.ModelRua;
import com.FarmaPet.FarmaPet.Model.Endereco.ModelUf;

import com.FarmaPet.FarmaPet.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private RuaRepository ruaRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private UfRepository ufRepository;

    public ModelEndereco salvarEndereco(DtoEndereco dto) {

        ModelRua rua = ruaRepository.findById(dto.ruaId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Rua não encontrada"));

        ModelBairro bairro = bairroRepository.findById(dto.bairroId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Bairro não encontrado"));

        ModelCidade cidade = cidadeRepository.findById(dto.cidadeId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));

        ModelUf uf = ufRepository.findById(dto.ufId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("UF não encontrada"));


        ModelEndereco endereco = new ModelEndereco();
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());

        return enderecoRepository.save(endereco);
    }
}

