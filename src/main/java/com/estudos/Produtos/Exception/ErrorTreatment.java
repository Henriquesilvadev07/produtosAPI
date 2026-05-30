package com.estudos.Produtos.Exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorTreatment {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratarErro404(RuntimeException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors()
                .stream()
                .map(DadosValidacaoErro :: new)
                .toList();
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
