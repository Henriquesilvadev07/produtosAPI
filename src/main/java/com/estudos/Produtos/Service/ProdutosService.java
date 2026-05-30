package com.estudos.Produtos.Service;

import com.estudos.Produtos.Dto.ProdutosDto;
import com.estudos.Produtos.Model.ProdutosModel;
import com.estudos.Produtos.Repository.ProdutosRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProdutosService {


    private ProdutosRepository produtosRepository;

    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    public ProdutosModel salvar(ProdutosDto dto) {
        ProdutosModel produtos = new ProdutosModel();

        produtos.setNome(dto.nome());
        produtos.setCategoria(dto.categoria());
        produtos.setPreco(dto.preco());
        produtos.setDescricao(dto.descricao());
        produtos.setEstoque(dto.estoque());

        return produtosRepository.save(produtos);
    }

    public List<ProdutosModel> Listar() {
        return produtosRepository.findAll();
    }

    public ProdutosModel acharPorId(Long id, ProdutosModel produtos) {
        return produtosRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Produto nåo existe!")
        );
    }

    public ProdutosModel atualizarPorId(Long id, ProdutosDto dto) {
        ProdutosModel produtos = produtosRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Produto não existe!")
        );

        produtos.setNome(dto.nome());
        produtos.setCategoria(dto.categoria());
        produtos.setPreco(dto.preco());
        produtos.setDescricao(dto.descricao());
        produtos.setEstoque(dto.estoque());

        return produtosRepository.saveAndFlush(produtos);
    }

    public void deletarPorId(Long id){
        if (produtosRepository.existsById(id)){
            produtosRepository.deleteById(id);
        }else{
           throw new RuntimeException("Produto não existe!");
        }

    }

}
