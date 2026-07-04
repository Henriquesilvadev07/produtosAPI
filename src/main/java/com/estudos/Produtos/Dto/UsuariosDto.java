package com.estudos.Produtos.Dto;

import jakarta.validation.constraints.NotBlank;

public record UsuariosDto(@NotBlank(message = "é necessário colocar o login") String login,
                          @NotBlank(message = "é necessário colocar a senha") String senha) {
}
