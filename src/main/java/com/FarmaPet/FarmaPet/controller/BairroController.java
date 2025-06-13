package com.FarmaPet.FarmaPet.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FarmaPet.FarmaPet.model.Endereco.ModelBairro;
import com.FarmaPet.FarmaPet.service.BairroService;

@RestController
@RequestMapping("/bairros")
public class BairroController {

    private final BairroService bairroService;

    public BairroController(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    @PostMapping
    public ResponseEntity<ModelBairro> criarBairro(@RequestBody ModelBairro bairro) {
        ModelBairro salvo = bairroService.salvarBairro(bairro);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<ModelBairro>> listarBairros() {
        return ResponseEntity.ok(bairroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelBairro> buscarPorId(@PathVariable int id) {
        return bairroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBairro(@PathVariable int id) {
        bairroService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
