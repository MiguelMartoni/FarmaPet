package com.FarmaPet.FarmaPet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.FarmaPet.FarmaPet.Model.ModelMedicamento;
import com.FarmaPet.FarmaPet.Service.MedicamentoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping("/medicamentos")
    public ResponseEntity<List<ModelMedicamento>> getAllMedicamentos() {
        List<ModelMedicamento> medicamentos = medicamentoService.findAll();
        if (medicamentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(medicamentos);
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicamentos);
    }

    @GetMapping("/medicamentos/{id}")
    public ResponseEntity<Object> getMedicamentoById(@PathVariable int id) {
        Optional<ModelMedicamento> medicamentoOptional = medicamentoService.findById(id);
        if (!medicamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoOptional.get());
    }

    @PostMapping("/medicamentos")
    public ResponseEntity<Object> saveMedicamento(@RequestBody @Valid ModelMedicamento medicamento) {
        var savedMedicamento = medicamentoService.save(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicamento);
    }

    @DeleteMapping("/medicamentos/{id}")
    public ResponseEntity<Object> deleteMedicamento(@PathVariable int id) {
        Optional<ModelMedicamento> medicamentoOptional = medicamentoService.findById(id);
        if (!medicamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }
        medicamentoService.delete(medicamentoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Medicamento deletado com sucesso.");
    }

    @PutMapping("/medicamentos/{id}")
    public ResponseEntity<Object> updateMedicamento(@PathVariable int id,
                                                    @RequestBody @Valid ModelMedicamento medicamento) {
        Optional<ModelMedicamento> medicamentoOptional = medicamentoService.findById(id);
        if (!medicamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }

        var medicamentoToUpdate = medicamentoOptional.get();
        BeanUtils.copyProperties(medicamento, medicamentoToUpdate, "id");
        // "id" fica protegido para não ser sobrescrito

        var updatedMedicamento = medicamentoService.save(medicamentoToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMedicamento);
    }
}
