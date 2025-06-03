package com.FarmaPet.FarmaPet.Controller;

import com.FarmaPet.FarmaPet.Model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.Model.ModelFuncionario;
import com.FarmaPet.FarmaPet.Model.ModelTipoCadastro;
import com.FarmaPet.FarmaPet.DTOs.DtoFuncionario;
import com.FarmaPet.FarmaPet.Repository.TipoCadastroRepository;
import com.FarmaPet.FarmaPet.Service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.FarmaPet.FarmaPet.Repository.EnderecoRepository;

import java.util.Optional;

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
