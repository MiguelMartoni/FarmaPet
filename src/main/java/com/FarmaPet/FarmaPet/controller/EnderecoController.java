package com.FarmaPet.FarmaPet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmaPet.FarmaPet.dtos.DtoEndereco;
import com.FarmaPet.FarmaPet.Model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.Repository.EnderecoRepository;
import com.FarmaPet.FarmaPet.Service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    public ResponseEntity<ModelEndereco> cadastrar(@RequestBody @Valid DtoEndereco dto) {
        ModelEndereco endereco = enderecoService.salvarEndereco(dto);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<ModelEndereco>> listar() {
        List<ModelEndereco> enderecos = enderecoRepository.findAll();
        return ResponseEntity.ok(enderecos);
    }
}
