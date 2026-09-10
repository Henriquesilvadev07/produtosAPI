package com.estudos.Produtos.Service;

import com.estudos.Produtos.Dto.ProdutosDto;
import com.estudos.Produtos.Model.CategoriaEnum;
import com.estudos.Produtos.Model.ProdutosModel;
import com.estudos.Produtos.Repository.ProdutosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProdutosServiceTest {

    @Mock
    private ProdutosRepository produtosRepository;

    @InjectMocks
    private ProdutosService produtosService;


    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Should save produtos")
    void salvar() {
        ProdutosDto dto = new ProdutosDto(
                "sabao",
                "sabonete de banho",
                BigDecimal.valueOf(2.99),
                CategoriaEnum.OUTROS,
                300);
        ProdutosModel produtos = new ProdutosModel();
        produtos.setNome(dto.nome());
        produtos.setCategoria(dto.categoria());
        produtos.setDescricao(dto.descricao());
        produtos.setPreco(dto.preco());
        produtos.setEstoque(dto.estoque());

        when(produtosRepository.save(any(ProdutosModel.class))).thenReturn(produtos);

        ProdutosModel produtosSalvos = produtosService.salvar(dto);

        assertNotNull(produtosSalvos);
        assertEquals("sabao", produtosSalvos.getNome());
    }

    @Test
    @DisplayName("Should list all products successfully")
    void listar() {
    }
}