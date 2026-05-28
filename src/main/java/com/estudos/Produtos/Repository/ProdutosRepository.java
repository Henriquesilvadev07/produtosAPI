package com.estudos.Produtos.Repository;

import com.estudos.Produtos.Model.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<ProdutosModel, Long> {
}
