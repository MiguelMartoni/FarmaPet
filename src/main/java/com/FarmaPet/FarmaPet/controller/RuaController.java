package com.FarmaPet.FarmaPet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FarmaPet.FarmaPet.model.Endereco.ModelRua;
import com.FarmaPet.FarmaPet.service.RuaService;

@RestController
@RequestMapping("/rua")
public class RuaController {

    @Autowired
    private RuaService ruaService;

    @PostMapping("/add")
    public ResponseEntity<ModelRua> addRua(@RequestBody ModelRua rua) {
        ModelRua savedRua = ruaService.saveRua(rua);
        return ResponseEntity.ok(savedRua);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ModelRua>> listRuas() {
        return ResponseEntity.ok(ruaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelRua> getRuaById(@PathVariable int id) {
        return ruaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRua(@PathVariable int id) {
        ruaService.deleteRua(id);
        return ResponseEntity.noContent().build();
    }
}
