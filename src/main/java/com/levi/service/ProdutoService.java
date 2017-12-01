package com.levi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi.model.Produto;
import com.levi.repository.ProdutoRepository;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> getProdutos(){
		List<Produto> produtos = new ArrayList<Produto>();
		produtoRepository.findAll().forEach(produtos::add);
		return produtos;
	}
	
	public Produto getProduto(Integer idProduto) {
		return produtoRepository.findOne(idProduto);
	}
	
	public void addProduto(Produto produto) {
		produtoRepository.save(produto);
	}

	public void updateProduto(Produto produto) {
		produtoRepository.save(produto);
	}

	public void deleteProduto(Integer idProduto) {
		produtoRepository.delete(idProduto);
	}
}



