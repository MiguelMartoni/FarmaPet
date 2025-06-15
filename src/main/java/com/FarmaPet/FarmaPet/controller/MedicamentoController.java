package com.FarmaPet.FarmaPet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.FarmaPet.FarmaPet.dtos.DtoMedicamento;
import com.FarmaPet.FarmaPet.dtos.DtoMovimentacaoEstoque;
import com.FarmaPet.FarmaPet.model.ModelMedicamento;
import com.FarmaPet.FarmaPet.service.MedicamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public ResponseEntity<List<ModelMedicamento>> getAllMedicamentos() {
        List<ModelMedicamento> medicamentos = medicamentoService.findAll();
        if (medicamentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(medicamentos);
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMedicamentoById(@PathVariable int id) {
        Optional<ModelMedicamento> medicamentoOptional = medicamentoService.findById(id);
        if (!medicamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveMedicamento(@RequestBody @Valid DtoMedicamento dto) {
        ModelMedicamento medicamento = new ModelMedicamento();
        preencherDados(medicamento, dto);

        var savedMedicamento = medicamentoService.save(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMedicamento(@PathVariable int id,
            @RequestBody @Valid DtoMedicamento dto) {
        Optional<ModelMedicamento> medicamentoOptional = medicamentoService.findById(id);
        if (!medicamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }

        var medicamento = medicamentoOptional.get();
        preencherDados(medicamento, dto); // atualiza os campos com os dados do DTO
        var updatedMedicamento = medicamentoService.save(medicamento);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMedicamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMedicamento(@PathVariable int id) {
        Optional<ModelMedicamento> medicamentoOptional = medicamentoService.findById(id);
        if (!medicamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }
        medicamentoService.delete(medicamentoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Medicamento deletado com sucesso.");
    }

    // Método auxiliar para preencher a entidade com dados do DTO
    private void preencherDados(ModelMedicamento medicamento, DtoMedicamento dto) {
        medicamento.setNome(dto.nome());
        medicamento.setPrincipioAtivo(dto.principioAtivo());
        medicamento.setDosagem(dto.dosagem());
        medicamento.setEspecieIndicada(dto.especieIndicada());
        medicamento.setDataValidade(java.sql.Date.valueOf(dto.dataValidade()));
        medicamento.setReceitaObrigatoria(dto.receitaObrigatoria());
        medicamento.setPesoIndicado(dto.pesoIndicado());
        medicamento.setIdadeIndicada(dto.idadeIndicada());
        medicamento.setTipoUso(dto.tipoUso());
        medicamento.setMedicamentoativo(dto.medicamentoativo());
        medicamento.setFoto(dto.foto());
        medicamento.setQuantidadeEstoque(dto.quantidadeEstoque());
    }

    @PutMapping("/{id}/estoque")
    public ResponseEntity<Object> movimentarEstoque(@PathVariable int id,
            @RequestBody @Valid DtoMovimentacaoEstoque dto) {
        Optional<ModelMedicamento> medicamentoOptional = medicamentoService.findById(id);
        if (!medicamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }

        ModelMedicamento medicamento = medicamentoOptional.get();
        int estoqueAtual = medicamento.getQuantidadeEstoque();

        int novaQuantidade;
        if (dto.tipo() == DtoMovimentacaoEstoque.TipoMovimentacao.ENTRADA) {
            novaQuantidade = estoqueAtual + dto.quantidade();
        } else { // SAIDA
            if (dto.quantidade() > estoqueAtual) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Quantidade em estoque insuficiente.");
            }
            novaQuantidade = estoqueAtual - dto.quantidade();
        }

        medicamento.setQuantidadeEstoque(novaQuantidade);
        medicamentoService.save(medicamento);

        return ResponseEntity.ok("Estoque atualizado com sucesso. Nova quantidade: " + novaQuantidade);
    }
}
