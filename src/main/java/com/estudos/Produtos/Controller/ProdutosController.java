package com.estudos.Produtos.Controller;

import com.estudos.Produtos.Dto.ProdutosDto;
import com.estudos.Produtos.Model.ProdutosModel;
import com.estudos.Produtos.Service.ProdutosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdutosController {


    private ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }


    @PostMapping
    public ResponseEntity<ProdutosModel> salvar(@Valid @RequestBody ProdutosDto dto, UriComponentsBuilder uriBuilder){
        var produtos = produtosService.salvar(dto);
        var uri = uriBuilder.path("/api/{id}").buildAndExpand(produtos.getId()).toUri();
        return ResponseEntity.created(uri).body(produtos);
    }


    @GetMapping
    public ResponseEntity<List<ProdutosModel>> listar() {
        var produto = produtosService.Listar();
        return ResponseEntity.status(201).body(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosModel> acharPorId(@PathVariable Long id){
        var produto = produtosService.acharPorId(id);
        return ResponseEntity.status(201).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutosModel> atualizarPorId(@PathVariable Long id, @RequestBody ProdutosDto dto){
        var atualizar = produtosService.atualizarPorId(id, dto);
        return ResponseEntity.status(201).body(atualizar);
    }

    @DeleteMapping("/{id}}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        return ResponseEntity.noContent().build();
    }

}
