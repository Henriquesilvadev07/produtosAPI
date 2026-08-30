package com.estudos.Produtos.Repository;

import com.estudos.Produtos.Dto.UsuariosDto;
import com.estudos.Produtos.Users.UsuariosModel;
import jakarta.persistence.EntityManager;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UsuariosRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UsuariosRepository usuariosRepository;

    private UsuariosModel createUser(UsuariosDto dto){
        UsuariosModel user = new UsuariosModel(dto);
        entityManager.persist(user);
        return user;
    }

    @Test
    @DisplayName("Verificar se usuario cadastra e existe no banco de dados")
    void findByLoginSucess(){
        String login = "henriquedev";
        UsuariosDto dto = new UsuariosDto(login, "1234");
        this.createUser(dto);
        Optional<UsuariosModel> resultado = this.usuariosRepository.findByLogin(login);
        assertThat(resultado.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Verificar se usuario nao existe no banco de dados")
    void findByLoginNotSucess(){
        String login = "henriquedev";

        Optional<UsuariosModel> resultado = this.usuariosRepository.findByLogin(login);
        assertThat(resultado.isEmpty()).isTrue();
    }

}