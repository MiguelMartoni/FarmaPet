package com.FarmaPet.FarmaPet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmaPet.FarmaPet.model.endereco.ModelUf;
import com.FarmaPet.FarmaPet.service.UfService;

@RestController
@RequestMapping("/uf")
public class UfController {

    private final UfService ufService;

    public UfController(UfService ufService) {
        this.ufService = ufService;
    }

    @GetMapping
    public List<ModelUf> listarTodos() {
        return ufService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelUf> buscarPorId(@PathVariable int id) {
        return ufService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ModelUf criarUf(@RequestBody ModelUf uf) {
        return ufService.salvarUf(uf);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelUf> atualizarUf(@PathVariable int id, @RequestBody ModelUf ufAtualizada) {
        try {
            ModelUf uf = ufService.atualizarUf(id, ufAtualizada);
            return ResponseEntity.ok(uf);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUf(@PathVariable int id) {
        ufService.deletarUf(id);
        return ResponseEntity.noContent().build();
    }
}
