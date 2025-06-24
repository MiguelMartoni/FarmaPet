package com.FarmaPet.FarmaPet.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.FarmaPet.FarmaPet.dtos.DtoMovimentacaoEstoqueMultipla;
import com.FarmaPet.FarmaPet.model.ModelMedicamento;
import com.FarmaPet.FarmaPet.service.MedicamentoService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
        if (medicamentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }
        return ResponseEntity.ok(medicamentoOptional.get());
    }

    @GetMapping("/relatorio-pdf")
    public ResponseEntity<byte[]> gerarRelatorioMedicamentosPdf() {
        try {
            List<ModelMedicamento> medicamentos = medicamentoService.findByQuantidadeEstoqueGreaterThan(1);
            System.out.println("Medicamentos encontrados: " + medicamentos.size());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);
            document.open();

            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph titulo = new Paragraph("Relatório de Medicamentos em Estoque (> 1)", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.addCell("ID");
            table.addCell("Nome");
            table.addCell("Princípio Ativo");
            table.addCell("Quantidade");
            table.addCell("Validade");

            for (ModelMedicamento m : medicamentos) {
                table.addCell(String.valueOf(m.getId()));
                table.addCell(m.getNome());
                table.addCell(m.getPrincipioAtivo());
                table.addCell(String.valueOf(m.getQuantidadeEstoque()));
                table.addCell(m.getDataValidade().toString());
            }

            document.add(table);
            document.close();

            byte[] pdfBytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "relatorio_medicamentos.pdf");

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveMedicamento(@RequestBody @Valid DtoMedicamento dto) {
        ModelMedicamento medicamento = new ModelMedicamento();
        preencherDados(medicamento, dto);
        var saved = medicamentoService.save(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMedicamento(@PathVariable int id, @RequestBody @Valid DtoMedicamento dto) {
        Optional<ModelMedicamento> optional = medicamentoService.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }
        var medicamento = optional.get();
        preencherDados(medicamento, dto);
        var updated = medicamentoService.save(medicamento);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMedicamento(@PathVariable int id) {
        Optional<ModelMedicamento> optional = medicamentoService.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }
        medicamentoService.delete(optional.get());
        return ResponseEntity.ok("Medicamento deletado com sucesso.");
    }

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
        medicamento.setQuantidadeEstoque(dto.quantidadeEstoque());
        if (dto.foto() != null) {
            medicamento.setFoto(dto.foto());
        }
    }

    @PutMapping("/{id}/estoque")
    public ResponseEntity<Object> movimentarEstoque(@PathVariable int id,
            @RequestBody @Valid DtoMovimentacaoEstoque dto) {
        Optional<ModelMedicamento> optional = medicamentoService.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }

        if (dto.quantidade() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantidade deve ser maior que zero.");
        }

        ModelMedicamento medicamento = optional.get();
        int estoqueAtual = medicamento.getQuantidadeEstoque();
        int novaQuantidade = dto.tipo() == DtoMovimentacaoEstoque.TipoMovimentacao.ENTRADA
                ? estoqueAtual + dto.quantidade()
                : estoqueAtual - dto.quantidade();

        if (novaQuantidade < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estoque insuficiente.");
        }

        medicamento.setQuantidadeEstoque(novaQuantidade);
        medicamentoService.save(medicamento);
        return ResponseEntity.ok("Estoque atualizado: " + novaQuantidade);
    }

    @PutMapping("/estoque/multiplos")
    public ResponseEntity<Object> movimentarEstoqueMultiplos(
            @RequestBody List<DtoMovimentacaoEstoqueMultipla> movimentacoes) {
        List<String> resultados = new ArrayList<>();

        for (DtoMovimentacaoEstoqueMultipla dto : movimentacoes) {
            Optional<ModelMedicamento> optional = medicamentoService.findById(dto.id());
            if (optional.isEmpty()) {
                resultados.add("Medicamento ID " + dto.id() + " não encontrado.");
                continue;
            }

            if (dto.quantidade() <= 0) {
                resultados.add("Quantidade inválida para ID " + dto.id());
                continue;
            }

            ModelMedicamento medicamento = optional.get();
            int estoqueAtual = medicamento.getQuantidadeEstoque();
            int novaQuantidade = dto.tipo() == DtoMovimentacaoEstoque.TipoMovimentacao.ENTRADA
                    ? estoqueAtual + dto.quantidade()
                    : estoqueAtual - dto.quantidade();

            if (novaQuantidade < 0) {
                resultados.add("Estoque insuficiente para ID " + dto.id());
                continue;
            }

            medicamento.setQuantidadeEstoque(novaQuantidade);
            medicamentoService.save(medicamento);
            resultados.add("Estoque atualizado para ID " + dto.id() + ": " + novaQuantidade);
        }

        return ResponseEntity.ok(resultados);
    }
}
