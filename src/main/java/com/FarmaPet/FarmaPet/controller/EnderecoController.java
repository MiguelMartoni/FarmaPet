package com.FarmaPet.FarmaPet.controller;

import java.util.List;

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

import com.FarmaPet.FarmaPet.dtos.DtoEndereco;
import com.FarmaPet.FarmaPet.model.endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<ModelEndereco> cadastrar(@RequestBody @Valid DtoEndereco dto) {
        ModelEndereco endereco = enderecoService.salvarEndereco(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    @GetMapping
    public ResponseEntity<List<ModelEndereco>> listarTodos() {
        List<ModelEndereco> enderecos = enderecoService.listarTodos();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelEndereco> buscarPorId(@PathVariable Long id) {
        ModelEndereco endereco = enderecoService.buscarPorId(id);
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelEndereco> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DtoEndereco dto) {
        ModelEndereco enderecoAtualizado = enderecoService.atualizarEndereco(id, dto);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}