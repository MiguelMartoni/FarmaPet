package com.FarmaPet.FarmaPet.Controller;

import com.FarmaPet.FarmaPet.Model.Endereço.ModelRua;
import com.FarmaPet.FarmaPet.Service.RuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
