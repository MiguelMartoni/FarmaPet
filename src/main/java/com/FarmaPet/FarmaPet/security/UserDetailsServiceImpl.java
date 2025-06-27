package com.FarmaPet.FarmaPet.security;

import com.FarmaPet.FarmaPet.model.ModelFuncionario;
import com.FarmaPet.FarmaPet.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== LOADING USER ===");
        System.out.println("Username: " + username);

        try {
            Optional<ModelFuncionario> funcionarioOpt = funcionarioRepository.findByNomeUsuario(username);

            if (funcionarioOpt.isPresent()) {
                ModelFuncionario funcionario = funcionarioOpt.get();
                System.out.println("✅ Usuário encontrado: " + funcionario.getNomeUsuario());
                System.out.println("Senha: " + funcionario.getSenha());
                System.out.println("Ativo: " + funcionario.isUsuarioAtivo());
                return new UserDetailsFuncionario(funcionario);
            } else {
                System.out.println("❌ Usuário não encontrado: " + username);
                throw new UsernameNotFoundException("Usuário não encontrado: " + username);
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar usuário: " + e.getMessage());
            throw new UsernameNotFoundException("Erro ao buscar usuário: " + username, e);
        }
    }
}