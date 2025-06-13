package com.FarmaPet.FarmaPet.Controller;

import com.FarmaPet.FarmaPet.DTOs.DtoEndereco;
import com.FarmaPet.FarmaPet.model.Endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.repository.EnderecoRepository;
import com.FarmaPet.FarmaPet.service.EnderecoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
