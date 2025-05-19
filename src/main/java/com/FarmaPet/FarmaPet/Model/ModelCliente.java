package com.FarmaPet.FarmaPet.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class ModelCliente extends ModelPessoa {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelAnimal> animais;

    public List<ModelAnimal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<ModelAnimal> animais) {
        this.animais = animais;
    }
}
