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

import com.FarmaPet.FarmaPet.dtos.DtoCliente;
import com.FarmaPet.FarmaPet.model.endereco.ModelEndereco;
import com.FarmaPet.FarmaPet.model.ModelCliente;
import com.FarmaPet.FarmaPet.repository.EnderecoRepository;
import com.FarmaPet.FarmaPet.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;
    private final EnderecoRepository enderecoRepo;

    public ClienteController(ClienteService service, EnderecoRepository enderecoRepo) {
        this.service = service;
        this.enderecoRepo = enderecoRepo;
    }

    @GetMapping
    public ResponseEntity<List<ModelCliente>> listar() {
        List<ModelCliente> lista = service.findAll();
        return lista.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscar(@PathVariable Integer id) {
        return service.findById(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado."));
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid DtoCliente dto) {
        Optional<ModelEndereco> enderecoOptional = enderecoRepo.findById(dto.enderecoId());
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não encontrado.");
        }

        var cliente = new ModelCliente(
                dto.nome(), dto.cpf(), dto.dataNasc(),
                enderecoOptional.get(), dto.email(), dto.telefone(), null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Integer id,
                                            @RequestBody @Valid DtoCliente dto) {
        Optional<ModelCliente> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        Optional<ModelEndereco> enderecoOptional = enderecoRepo.findById(dto.enderecoId());
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereço não encontrado.");
        }

        var cliente = optional.get();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setDataNasc(dto.dataNasc());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setEndereco(enderecoOptional.get());

        return ResponseEntity.ok(service.update(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Integer id) {
        Optional<ModelCliente> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        service.delete(optional.get());
        return ResponseEntity.ok("Cliente removido com sucesso.");
    }
}
