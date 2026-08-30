package com.estudos.Produtos.Repository;

import com.estudos.Produtos.Users.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long> {

    Optional<UserDetails> findByLogin(String login);

}
