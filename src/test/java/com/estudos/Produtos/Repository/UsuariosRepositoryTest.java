package com.estudos.Produtos.Repository;

import com.estudos.Produtos.Dto.UsuariosDto;
import com.estudos.Produtos.Users.UsuariosModel;
import jakarta.persistence.EntityManager;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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

}