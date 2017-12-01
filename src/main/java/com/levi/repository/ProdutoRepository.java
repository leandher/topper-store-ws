package com.levi.repository;

import org.springframework.data.repository.CrudRepository;

import com.levi.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

	
}
