package com.FarmaPet.FarmaPet.model;

import com.FarmaPet.FarmaPet.model.endereco.ModelEndereco;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "funcionario")
public class ModelFuncionario extends ModelPessoa {

    @Column(nullable = false, length = 50)
    private String nomeUsuario;

    @Column(nullable = false, length = 18)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "tipo_cadastro_id")
    private ModelTipoCadastro tipoCadastro;

    @Column(nullable = false)
    private boolean usuarioAtivo;

    public ModelFuncionario(){}

    public ModelFuncionario(String nome, String cpf, LocalDate dataNasc, ModelEndereco endereco, String email, String telefone, String nomeUsuario, String senha, ModelTipoCadastro tipoCadastro, boolean usuarioAtivo) {
        super(nome, cpf, dataNasc, endereco, email, telefone);
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipoCadastro = tipoCadastro;
        this.usuarioAtivo = usuarioAtivo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ModelTipoCadastro getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(ModelTipoCadastro tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public boolean isUsuarioAtivo() {
        return usuarioAtivo;
    }

    public void setUsuarioAtivo(boolean usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }
}
