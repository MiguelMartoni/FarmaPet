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
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return funcionario.getSenha();
    }

    @Override
    public String getUsername() {
        return funcionario.getNomeUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public ModelFuncionario getFuncionario() {
        return funcionario;
    }
}