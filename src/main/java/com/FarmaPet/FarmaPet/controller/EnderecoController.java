package com.FarmaPet.FarmaPet.controller;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FarmaPet.FarmaPet.dtos.DtoEndereco;
import com.FarmaPet.FarmaPet.model.endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.repository.EnderecoRepository;
import com.FarmaPet.FarmaPet.service.EnderecoService;

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

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ModelEndereco> buscarPorId(@PathVariable Long id) {
        Optional<ModelEndereco> endereco = enderecoRepository.findById(id);
        return endereco.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ModelEndereco> atualizar(@PathVariable Long id,
                                                   @RequestBody @Valid DtoEndereco dto) {
        Optional<ModelEndereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            ModelEndereco enderecoAtualizado = enderecoService.atualizarEndereco(id, dto);
            return ResponseEntity.ok(enderecoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            enderecoService.deleteById(id);
            return ResponseEntity.ok("Endereço deletado com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
