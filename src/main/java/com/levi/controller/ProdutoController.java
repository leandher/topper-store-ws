package com.levi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levi.model.Produto;
import com.levi.service.ProdutoService;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping("/produtos")
	public List<Produto> getAllProdutos() {
		return produtoService.getProdutos();
	}
	
	@RequestMapping("/produtos/{idProduto}")
	public Produto getProduto(@PathVariable Integer idProduto) {
		return produtoService.getProduto(idProduto);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/produtos")
	public void addProduto(@RequestBody Produto produto) {
		produtoService.addProduto(produto);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/produtos/{idProduto}")
	public void updateProduto(@RequestBody Produto produto, @PathVariable Integer idProduto) {
		produtoService.updateProduto(produto);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/produtos/{idProduto}")
	public void deleteProduto(@PathVariable Integer idProduto) {
		produtoService.deleteProduto(idProduto);
	}
}
