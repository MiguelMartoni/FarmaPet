package com.FarmaPet.FarmaPet.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FarmaPet.FarmaPet.Model.Endereco.ModelCidade;
import com.FarmaPet.FarmaPet.Service.CidadeService;

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
