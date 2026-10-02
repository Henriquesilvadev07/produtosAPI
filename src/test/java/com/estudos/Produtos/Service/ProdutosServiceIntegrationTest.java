package com.estudos.Produtos.Service;

import com.estudos.Produtos.Repository.ProdutosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutosServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Test
    @DisplayName("deve retornar sucesso ao salvar produto com dados validos")
    @WithMockUser(username = "operador", roles = {"USER"})
    void salvarProdutoComSucesso() throws Exception {

        String jsonPayLoad =    """
                {
                "nome" : "sabonete",
                "descricao" : "produto de higiene pessoal",
                "preco" : "2.90",
                "categoria" : "OUTROS",
                "estoque" : 200
                }
                """;

        mockMvc.perform(post("/api")
                .contentType(APPLICATION_JSON)
                .content(jsonPayLoad))
                .andExpect(status().isCreated());


        boolean salvoNoBanco = produtosRepository.existsByNome("sabonete");
        assertTrue(salvoNoBanco, "o produto foi salvo no banco de dados com sucesso");


    }




}
