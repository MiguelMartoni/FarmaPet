package com.FarmaPet.FarmaPet.controller;

import com.FarmaPet.FarmaPet.service.RuaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.FarmaPet.FarmaPet.model.endereco.ModelRua;

@RestController
@RequestMapping("/ruas")  // Mudei para plural seguindo convenções REST
public class RuaController {

    private final RuaService ruaService;

    // Injeção por construtor (mais seguro que @Autowired em campo)
    public RuaController(RuaService ruaService) {
        this.ruaService = ruaService;
    }

    @PostMapping
    public ResponseEntity<ModelRua> cadastrarRua(@RequestBody ModelRua rua) {
        ModelRua novaRua = ruaService.saveRua(rua);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRua);
    }

    @GetMapping
    public ResponseEntity<List<ModelRua>> listarTodas() {
        List<ModelRua> ruas = ruaService.listAll();
        return ResponseEntity.ok(ruas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelRua> buscarPorId(@PathVariable int id) {
        return ruaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-nome/{nome}")
    public ResponseEntity<ModelRua> buscarPorNome(@PathVariable String nome) {
        return ruaService.findByDescricao(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRua(@PathVariable int id) {
        ruaService.deleteRua(id);
        return ResponseEntity.noContent().build();
    }
}