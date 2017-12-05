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

import com.levi.model.Item;
import com.levi.service.ItemService;
import com.levi.service.PedidoService;
import com.levi.service.ProdutoService;

@Transactional
@CrossOrigin("*")
@RequestMapping("/api/items")
@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value ="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Item> getAllItems() {
		return itemService.getItems();
	}
	
	@RequestMapping(value = "/{idItem}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Item getItem(@PathVariable Integer idItem) {
		return itemService.getItem(idItem);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		try{
			itemService.addItem(item);	
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		} catch(DataIntegrityViolationException e){
			return new ResponseEntity<Item>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{idItem}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable Integer idItem) {
		
		try{
			itemService.updateItem(item);	
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		} catch(DataIntegrityViolationException e){
			return new ResponseEntity<Item>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/{idItem}")
	public ResponseEntity<Item> deleteItem(@PathVariable Integer idItem) {
		itemService.deleteItem(idItem);
		return new ResponseEntity<Item>(HttpStatus.OK);
	}
	
	@RequestMapping("/itensDoPedido/{idPedido}")
	public List<Item> getAllItensDoPedido(@PathVariable Integer idPedido) {
		return pedidoService.getPedido(idPedido).getItens();
	}
	
	@RequestMapping("/itemDoPedido/{idItem}")
	public Item getItemDoPedido(@PathVariable Integer idItem) {
		return itemService.getItemDoPedido(idItem);
	}
	
	@RequestMapping("/itensDoProduto/{idProduto}")
	public List<Item> getAllItensDoProduto(@PathVariable Integer idProduto) {
		return produtoService.getProduto(idProduto).getItens();
	}
	
	@RequestMapping("/itemDoProduto/{idItem}")
	public Item getItemDoProduto(@PathVariable Integer idItem) {
		return itemService.getItemDoProduto(idItem);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{idPedido}")
	public void addItemNoPedido(@RequestBody Item item, @PathVariable Integer idPedido) {
		itemService.addItemNoPedido(idPedido, item);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{idProduto}")
	public void addItemNoProduto(@RequestBody Item item, @PathVariable Integer idProduto) {
		itemService.addItemNoProduto(idProduto, item);
	}
}
