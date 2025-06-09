package com.FarmaPet.FarmaPet.Security;

import com.FarmaPet.FarmaPet.Model.ModelFuncionario;
import com.FarmaPet.FarmaPet.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ModelFuncionario funcionario = funcionarioRepository.findByNomeUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado: " + username));

        return new UserDetailsFuncionario(funcionario);
    }
}
