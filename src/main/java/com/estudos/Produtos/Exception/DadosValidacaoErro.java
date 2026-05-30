package com.estudos.Produtos.Exception;

import org.springframework.validation.FieldError;

public record DadosValidacaoErro(String campo, String mensagem) {
    public DadosValidacaoErro(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
