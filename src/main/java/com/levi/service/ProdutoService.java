package com.levi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi.dto.VendaMensal;
import com.levi.dto.VendaPorCategoria;
import com.levi.dto.VendaProduto;
import com.levi.model.Produto;
import com.levi.repository.ProdutoRepository;

import javassist.bytecode.Descriptor.Iterator;

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
	
	public List<VendaPorCategoria> getVendaPorCategoria() {
		List<VendaPorCategoria> vendasPorCategoria = produtoRepository.calcularVendaPorCategoria();
		VendaPorCategoria vendaPorCategoria1 = null;
		VendaPorCategoria vendaPorCategoria2;
		VendaPorCategoria vendaPorCategoriaAux;
		java.util.Iterator<VendaPorCategoria> it1 = vendasPorCategoria.iterator();
		java.util.Iterator<VendaPorCategoria> it2 = vendasPorCategoria.iterator();
		vendaPorCategoria2 = it2.next();
		while(it1.hasNext()) {
			it2 = vendasPorCategoria.iterator();
			vendaPorCategoria1 = it1.next();
			while(it2.hasNext()) {
				vendaPorCategoria2 = it2.next();
				if(vendaPorCategoria1.getValor() > vendaPorCategoria2.getValor()){
					vendaPorCategoriaAux = vendaPorCategoria1;
					vendaPorCategoria1 = vendaPorCategoria2;
					vendaPorCategoria2 = vendaPorCategoriaAux;
				}
			}
		}
		while(it1.hasNext()) {
			vendasPorCategoria.add(it1.next());
		}
		return vendasPorCategoria;
		
	}
	
	public List<VendaMensal> getVendaMensal() {
		return produtoRepository.calcularVendaMensal();
	}
	
	public List<VendaProduto> getVendaProduto() {
		return produtoRepository.calcularVendaProduto();
	}
}



