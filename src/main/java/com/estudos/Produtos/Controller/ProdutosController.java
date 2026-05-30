package com.estudos.Produtos.Controller;

import com.estudos.Produtos.Dto.ProdutosDto;
import com.estudos.Produtos.Model.ProdutosModel;
import com.estudos.Produtos.Service.ProdutosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


    public ResponseEntity<ProdutosModel> salvar(@RequestBody ProdutosDto dto, UriComponentsBuilder uriBuilder){
        var produtos = produtosService.salvar(dto);
        var uri = uriBuilder.path("/api/{id}").buildAndExpand(produtos.getId()).toUri();
        return ResponseEntity.created(uri).body(produtos);
    }


    public ResponseEntity<List<ProdutosModel>> listar() {
        var produto = produtosService.Listar();
        return ResponseEntity.status(201).body(produto);
    }

    public ResponseEntity<ProdutosModel> acharPorId(@PathVariable Long id){
        var produto = produtosService.acharPorId(id);
        return ResponseEntity.status(201).body(produto);
    }

    public ResponseEntity<ProdutosModel> atualizarPorId(@PathVariable Long id, @RequestBody ProdutosDto dto){
        var atualizar = produtosService.atualizarPorId(id, dto);
        return ResponseEntity.status(201).body(atualizar);
    }

    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        return ResponseEntity.noContent().build();
    }

}
