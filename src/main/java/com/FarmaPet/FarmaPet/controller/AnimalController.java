package com.FarmaPet.FarmaPet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

import com.FarmaPet.FarmaPet.Model.ModelAnimal;
import com.FarmaPet.FarmaPet.Service.AnimalService;
import com.FarmaPet.FarmaPet.dtos.DtoAnimal;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    /* ========== GET /animais ========== */
    @GetMapping
    public ResponseEntity<List<ModelAnimal>> listar() {
        List<ModelAnimal> animais = service.findAll();
        if (animais.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(animais);
        }
        return ResponseEntity.ok(animais);
    }

    /* ========== GET /animais/{id} ========== */
    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhes(@PathVariable Integer id) {
        Optional<ModelAnimal> optional = service.findById(id);
        return optional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Animal não encontrado."));
    }

    /* ========== POST /animais ========== */
    @PostMapping
    public ResponseEntity<ModelAnimal> criar(@RequestBody @Valid DtoAnimal dto) {
        var entity = new ModelAnimal(dto.especie(), 0, dto.idade(), dto.nome(),
                dto.peso(), dto.raca());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }

    /* ========== PUT /animais/{id} ========== */
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Integer id,
            @RequestBody @Valid DtoAnimal dto) {

        Optional<ModelAnimal> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
        }

        var entity = optional.get();
        BeanUtils.copyProperties(dto, entity, "id"); // mantém o mesmo id
        return ResponseEntity.ok(service.update(entity));
    }

    /* ========== DELETE /animais/{id} ========== */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Integer id) {
        Optional<ModelAnimal> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
        }
        service.delete(optional.get());
        return ResponseEntity.ok("Animal removido com sucesso.");
    }
}
