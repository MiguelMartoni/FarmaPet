package com.FarmaPet.FarmaPet.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmaPet.FarmaPet.dtos.DtoFuncionario;
import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import com.FarmaPet.FarmaPet.model.ModelTipoCadastro;
import com.FarmaPet.FarmaPet.repository.EnderecoRepository;
import com.FarmaPet.FarmaPet.repository.TipoCadastroRepository;
import com.FarmaPet.FarmaPet.service.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    TipoCadastroRepository tipoCadastroRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid DtoFuncionario dto) {
        Optional<ModelTipoCadastro> tipoCadastroOpt = tipoCadastroRepository.findById(dto.tipoCadastroId());
        if (tipoCadastroOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de cadastro inválido");
        }

        Optional<ModelEndereco> enderecoOpt = enderecoRepository.findById(dto.enderecoId());
        if (enderecoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço inválido");
        }

        var funcionario = new ModelFuncionario();
        BeanUtils.copyProperties(dto, funcionario);
        funcionario.setTipoCadastro(tipoCadastroOpt.get());
        funcionario.setEndereco(enderecoOpt.get());
        funcionario.setDataNasc(dto.dataNasc());
        if (dto.foto() != null) {
            funcionario.setFoto(dto.foto());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionario));
    }


    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(funcionarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<ModelFuncionario> funcionarioOpt = funcionarioService.findById(id);
        if (funcionarioOpt.isPresent()) {
            return ResponseEntity.ok(funcionarioOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid DtoFuncionario dto) {
        Optional<ModelFuncionario> funcionarioOpt = funcionarioService.findById(id);
        if (funcionarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }

        Optional<ModelTipoCadastro> tipoCadastroOpt = tipoCadastroRepository.findById(dto.tipoCadastroId());
        if (tipoCadastroOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de cadastro inválido");
        }

        var funcionario = funcionarioOpt.get();
        BeanUtils.copyProperties(dto, funcionario);
        funcionario.setId(id);
        funcionario.setTipoCadastro(tipoCadastroOpt.get());
        if (dto.foto() != null) {
            funcionario.setFoto(dto.foto());
        }

        return ResponseEntity.ok(funcionarioService.save(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<ModelFuncionario> funcionarioOpt = funcionarioService.findById(id);
        if (funcionarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }

        funcionarioService.delete(funcionarioOpt.get());
        return ResponseEntity.ok("Funcionário deletado com sucesso");
    }
}
