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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        ProdutosModel produtos1 = new ProdutosModel();
        produtos1.setNome("Sabao");
        produtos1.setCategoria(CategoriaEnum.OUTROS);
        produtos1.setDescricao("Sabunete corporal");
        produtos1.setPreco(BigDecimal.valueOf(2.99));
        produtos1.setEstoque(200);

        ProdutosModel produtos2 = new ProdutosModel();
        produtos2.setNome("Miojo");
        produtos2.setCategoria(CategoriaEnum.ALIMENTO);
        produtos2.setDescricao("Lamen rapido");
        produtos2.setPreco(BigDecimal.valueOf(3.42));
        produtos2.setEstoque(232);

        List<ProdutosModel> listarMocks = Arrays.asList(produtos1, produtos2);

        when(produtosRepository.findAll()).thenReturn(listarMocks);

        List<ProdutosModel> produtosSalvos = produtosService.Listar();

        assertNotNull(produtosSalvos);
        assertEquals(2, produtosSalvos.size());
        assertEquals("Sabao", produtosSalvos.get(0).getNome());
        assertEquals("Miojo", produtosSalvos.get(1).getNome());
    }

    @Test
    @DisplayName("Should delete the Produtos successfully")
    void deletarWithSucess() {
        Long id = 1L;
        when(produtosRepository.existsById(id)).thenReturn(true);
        produtosService.deletarPorId(id);
        verify(produtosRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should throw exception when try to delete with invalid id")
    void deletarWithException() {
        Long id = 50L;
        when(produtosRepository.existsById(id)).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            produtosService.deletarPorId(id);
        });
        assertEquals("Produto não existe!", exception.getMessage());
        verify(produtosRepository, never()).deleteById(id);
    }
}