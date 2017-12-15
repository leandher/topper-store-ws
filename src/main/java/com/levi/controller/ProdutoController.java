package com.levi.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levi.dto.VendaMensal;
import com.levi.dto.VendaPorCategoria;
import com.levi.dto.VendaProduto;
import com.levi.model.Produto;
import com.levi.service.ProdutoService;

@Transactional
@CrossOrigin("*")
@RequestMapping("/api/produtos")
@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value ="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Produto> getAllProdutos() {
		return produtoService.getProdutos();
	}
	
	@RequestMapping(value = "/{idProduto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Produto getProduto(@PathVariable Integer idProduto) {
		return produtoService.getProduto(idProduto);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> addProduto(@RequestBody Produto produto) {
		try{
			produtoService.addProduto(produto);	
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		} catch(DataIntegrityViolationException e){
			return new ResponseEntity<Produto>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{idProduto}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, @PathVariable Integer idProduto) {
		
		try{
			produtoService.updateProduto(produto);	
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		} catch(DataIntegrityViolationException e){
			return new ResponseEntity<Produto>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{idProduto}")
	public ResponseEntity<Produto> deleteProduto(@PathVariable Integer idProduto) {
		produtoService.deleteProduto(idProduto);
		return new ResponseEntity<Produto>(HttpStatus.OK);
	}
	
	@RequestMapping(value ="/vendaPorCategoria", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaPorCategoria> getVendaPorCategoria() {
		return produtoService.getVendaPorCategoria();
	}
	
	@RequestMapping(value ="/vendaMensal", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaMensal> getVendaMensal() {
		return produtoService.getVendaMensal();
	}
	
	@RequestMapping(value ="/vendaProduto", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaProduto> getVendaProduto() {
		return produtoService.getVendaProduto();
	}
}
