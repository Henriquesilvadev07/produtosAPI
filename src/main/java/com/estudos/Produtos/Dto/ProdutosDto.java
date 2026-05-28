package com.estudos.Produtos.Dto;

import com.estudos.Produtos.Model.CategoriaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutosDto(@NotBlank(message = "O nome é Obrigatório!") String nome,
                          @NotBlank(message = "A descricao é Obrigatória!") String descricao,
                          @NotNull(message = "O preco é Obrigatório!") BigDecimal preco,
                          @NotNull(message = "A categoria é Obrigatória!") CategoriaEnum categoria,
                          @NotNull(message = "O estoque é Obrigatório!") Integer estoque) {
}
