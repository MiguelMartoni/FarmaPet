package com.FarmaPet.FarmaPet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.FarmaPet.FarmaPet.dtos.DtoEndereco;
import com.FarmaPet.FarmaPet.model.ModelCliente;
import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import com.FarmaPet.FarmaPet.model.endereco.ModelBairro;
import com.FarmaPet.FarmaPet.model.endereco.ModelCidade;
import com.FarmaPet.FarmaPet.model.endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.endereco.ModelRua;
import com.FarmaPet.FarmaPet.model.endereco.ModelUf;
import com.FarmaPet.FarmaPet.repository.BairroRepository;
import com.FarmaPet.FarmaPet.repository.CidadeRepository;
import com.FarmaPet.FarmaPet.repository.EnderecoRepository;
import com.FarmaPet.FarmaPet.repository.RuaRepository;
import com.FarmaPet.FarmaPet.repository.UfRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final RuaRepository ruaRepository;
    private final BairroRepository bairroRepository;
    private final CidadeRepository cidadeRepository;
    private final UfRepository ufRepository;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository,
                         RuaRepository ruaRepository,
                         BairroRepository bairroRepository,
                         CidadeRepository cidadeRepository,
                         UfRepository ufRepository,
                         ClienteService clienteService,
                         FuncionarioService funcionarioService) {
        this.enderecoRepository = enderecoRepository;
        this.ruaRepository = ruaRepository;
        this.bairroRepository = bairroRepository;
        this.cidadeRepository = cidadeRepository;
        this.ufRepository = ufRepository;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
    }

    @Transactional
    public ModelEndereco salvarEndereco(@Valid DtoEndereco dto) {
        validarDtoEndereco(dto);

        ModelEndereco endereco = new ModelEndereco();
        configurarRelacionamentos(endereco, dto);
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());

        return enderecoRepository.save(endereco);
    }

    @Transactional
    public ModelEndereco atualizarEndereco(Long id, @Valid DtoEndereco dto) {
        validarDtoEndereco(dto);

        ModelEndereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));

        configurarRelacionamentos(endereco, dto);
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());

        return enderecoRepository.save(endereco);
    }

    public ModelEndereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));
    }

    public List<ModelEndereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    @Transactional
    public void deletarEndereco(Long id) {
        ModelEndereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));

        desvincularClientes(endereco);
        desvincularFuncionarios(endereco);

        enderecoRepository.delete(endereco);
    }

    // ----------------------------------------------
    // Validação robusta do DTO
    private void validarDtoEndereco(DtoEndereco dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO de endereço não pode ser nulo");
        }
        if (dto.ruaId() == null) {
            throw new IllegalArgumentException("ID da rua é obrigatório");
        }
        if (dto.bairroId() == null) {
            throw new IllegalArgumentException("ID do bairro é obrigatório");
        }
        if (dto.cidadeId() == null) {
            throw new IllegalArgumentException("ID da cidade é obrigatório");
        }
        if (dto.ufId() == null) {
            throw new IllegalArgumentException("ID da UF é obrigatório");
        }
    }

    // Configura os relacionamentos de forma segura
    private void configurarRelacionamentos(ModelEndereco endereco, DtoEndereco dto) {
        endereco.setRua(buscarRua(dto.ruaId()));
        endereco.setBairro(buscarBairro(dto.bairroId()));
        endereco.setCidade(buscarCidade(dto.cidadeId()));
        endereco.setUf(buscarUf(dto.ufId()));
    }

    // Métodos auxiliares para buscar cada entidade, validando null e existência
    private ModelRua buscarRua(Long id) {
        if (id == null) throw new IllegalArgumentException("ID da rua não pode ser nulo");
        return ruaRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFoundException("Rua não encontrada com ID: " + id));
    }

    private ModelBairro buscarBairro(Long id) {
        if (id == null) throw new IllegalArgumentException("ID do bairro não pode ser nulo");
        return bairroRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFoundException("Bairro não encontrado com ID: " + id));
    }

    private ModelCidade buscarCidade(Long id) {
        if (id == null) throw new IllegalArgumentException("ID da cidade não pode ser nulo");
        return cidadeRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada com ID: " + id));
    }

    private ModelUf buscarUf(Long id) {
        if (id == null) throw new IllegalArgumentException("ID da UF não pode ser nulo");
        return ufRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFoundException("UF não encontrada com ID: " + id));
    }

    // Métodos para desvincular clientes e funcionários antes de deletar endereço
    private void desvincularClientes(ModelEndereco endereco) {
        List<ModelCliente> clientes = clienteService.findByEndereco(endereco);
        clientes.forEach(cliente -> {
            cliente.setEndereco(null);
            clienteService.save(cliente);
        });
    }

    private void desvincularFuncionarios(ModelEndereco endereco) {
        List<ModelFuncionario> funcionarios = funcionarioService.findByEndereco(endereco);
        funcionarios.forEach(funcionario -> {
            funcionario.setEndereco(null);
            funcionarioService.save(funcionario);
        });
    }
}
