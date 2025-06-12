package com.FarmaPet.FarmaPet.security;

import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsFuncionario implements UserDetails {

    private final ModelFuncionario funcionario;

    public UserDetailsFuncionario(ModelFuncionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Nenhuma role/authority definida por enquanto
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return funcionario.getSenha(); // Campo senha no ModelFuncionario
    }

    @Override
    public String getUsername() {
        return funcionario.getNomeUsuario(); // Pode ser substituído por email ou CPF
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Personalize se necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Personalize se necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Personalize se necessário
    }

    @Override
    public boolean isEnabled() {
        // Aqui o login só será permitido se houver tipo de cadastro associado
        return funcionario.getTipoCadastro() != null;
    }

    public ModelFuncionario getFuncionario() {
        return funcionario;
    }
}
