package com.estudos.Produtos.Service;

import com.estudos.Produtos.Model.CategoriaEnum;
import com.estudos.Produtos.Model.ProdutosModel;
import com.estudos.Produtos.Repository.ProdutosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Test
    @DisplayName("deve retornar sucesso ao listar todos os produtos salvos")
    @WithMockUser(username = "operador", roles = {"USER"})
    void listarProdutoComSucesso() throws Exception{

        ProdutosModel produtos = new ProdutosModel();
        produtos.setNome("sabao");
        produtos.setCategoria(CategoriaEnum.OUTROS);
        produtos.setDescricao("Sabunete corporal");
        produtos.setPreco(BigDecimal.valueOf(2.99));
        produtos.setEstoque(200);
        produtosRepository.save(produtos);

        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nome").value("sabao"));


    }

    @Test
    @DisplayName("deve retornar sucesso ao procurar por um id valido")
    @WithMockUser(username = "operador", roles = {"USER"})
    void acharPorIdComSucesso() throws Exception{

        ProdutosModel produtos = new ProdutosModel();
        produtos.setNome("sabao");
        produtos.setCategoria(CategoriaEnum.OUTROS);
        produtos.setDescricao("Sabunete corporal");
        produtos.setPreco(BigDecimal.valueOf(2.99));
        produtos.setEstoque(200);
        produtosRepository.save(produtos);



    }


}
