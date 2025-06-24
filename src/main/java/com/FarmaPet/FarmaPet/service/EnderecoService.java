package com.FarmaPet.FarmaPet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FarmaPet.FarmaPet.dtos.DtoEndereco;
import com.FarmaPet.FarmaPet.model.Endereco.ModelBairro;
import com.FarmaPet.FarmaPet.model.Endereco.ModelCidade;
import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.Endereco.ModelRua;
import com.FarmaPet.FarmaPet.model.Endereco.ModelUf;
import com.FarmaPet.FarmaPet.model.ModelCliente;
import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import com.FarmaPet.FarmaPet.repository.BairroRepository;
import com.FarmaPet.FarmaPet.repository.CidadeRepository;
import com.FarmaPet.FarmaPet.repository.EnderecoRepository;
import com.FarmaPet.FarmaPet.repository.RuaRepository;
import com.FarmaPet.FarmaPet.repository.UfRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

    @Autowired ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

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

    public ModelEndereco atualizarEndereco(Long id, DtoEndereco dto) {
        ModelEndereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        ModelRua rua = ruaRepository.findById(dto.ruaId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Rua não encontrada"));

        ModelBairro bairro = bairroRepository.findById(dto.bairroId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Bairro não encontrado"));

        ModelCidade cidade = cidadeRepository.findById(dto.cidadeId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));

        ModelUf uf = ufRepository.findById(dto.ufId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("UF não encontrada"));

        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setUf(uf);
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());

        return enderecoRepository.save(endereco);
    }

    public ModelEndereco findById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));
    }

    @Transactional
    public void deleteById(Long id) {
        ModelEndereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        List<ModelFuncionario> funcionarios = funcionarioService.findByEndereco(endereco);
        for (ModelFuncionario funcionario : funcionarios) {
            funcionario.setEndereco(null);
            funcionarioService.save(funcionario);
        }

        List<ModelCliente> clientes = clienteService.findByEndereco(endereco);
        for (ModelCliente cliente : clientes) {
            cliente.setEndereco(null);
            clienteService.save(cliente);
        }
        enderecoRepository.deleteById(id);
    }

}
