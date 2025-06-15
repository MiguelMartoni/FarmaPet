package com.FarmaPet.FarmaPet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmaPet.FarmaPet.model.Endereco.ModelCidade;
import com.FarmaPet.FarmaPet.service.CidadeService;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<ModelCidade> listar() {
        return cidadeService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelCidade> buscarPorId(@PathVariable int id) {
        Optional<ModelCidade> cidade = cidadeService.buscarPorId(id);
        return cidade.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ModelCidade cadastrar(@RequestBody ModelCidade cidade) {
        return cidadeService.salvar(cidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelCidade> atualizar(@PathVariable int id, @RequestBody ModelCidade cidade) {
        ModelCidade atualizada = cidadeService.atualizar(id, cidade);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        cidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
