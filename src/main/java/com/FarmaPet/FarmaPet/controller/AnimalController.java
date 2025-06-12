package com.FarmaPet.FarmaPet.controller;

import java.util.List;
import java.util.Optional;

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

import com.FarmaPet.FarmaPet.dtos.DtoAnimal;
import com.FarmaPet.FarmaPet.model.ModelAnimal;
import com.FarmaPet.FarmaPet.model.ModelCliente;
import com.FarmaPet.FarmaPet.repository.AnimalRepository;
import com.FarmaPet.FarmaPet.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalRepository animalRepo;
    private final ClienteRepository clienteRepo;

    public AnimalController(AnimalRepository animalRepo, ClienteRepository clienteRepo) {
        this.animalRepo = animalRepo;
        this.clienteRepo = clienteRepo;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DtoAnimal dto) {
        Optional<ModelCliente> clienteOpt = clienteRepo.findById(dto.clienteId());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado.");
        }

        ModelAnimal animal = new ModelAnimal();
        animal.setNome(dto.nome());
        animal.setEspecie(dto.especie());
        animal.setRaca(dto.raca());
        animal.setIdade(dto.idade());
        animal.setPeso(dto.peso());
        animal.setCliente(clienteOpt.get());

        animalRepo.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(animal);
    }

    @GetMapping
    public ResponseEntity<List<ModelAnimal>> listar() {
        List<ModelAnimal> lista = animalRepo.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<ModelAnimal> animalOpt = animalRepo.findById(id);
        if (animalOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
        }
        return ResponseEntity.ok(animalOpt.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @RequestBody @Valid DtoAnimal dto) {
        Optional<ModelAnimal> animalOpt = animalRepo.findById(id);
        if (animalOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
        }

        Optional<ModelCliente> clienteOpt = clienteRepo.findById(dto.clienteId());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado.");
        }

        ModelAnimal animal = animalOpt.get();
        animal.setNome(dto.nome());
        animal.setEspecie(dto.especie());
        animal.setRaca(dto.raca());
        animal.setIdade(dto.idade());
        animal.setPeso(dto.peso());
        animal.setCliente(clienteOpt.get());

        animalRepo.save(animal);
        return ResponseEntity.ok(animal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable int id) {
        Optional<ModelAnimal> animalOpt = animalRepo.findById(id);
        if (animalOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
        }

        animalRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
