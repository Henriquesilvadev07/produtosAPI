package com.estudos.Produtos.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutosServiceIntegrationTest {

    @Test
    @DisplayName("deve retornar sucesso ao salvar produto com dados validos")
    @WithMockUser(username = "operador", roles = {"USER"})
    void salvarProdutoComSucesso() {

        String jsonPayLoad =    """
                {
                "nome" : "sabonete",
                "descricao" : "produto de higiene pessoal",
                "preco" : "2.90",
                "categoria" : "OUTROS",
                "estoque" : 200
                }
                """;

    }




}
