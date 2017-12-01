package com.levi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levi.model.Item;
import com.levi.service.ItemService;
import com.levi.service.PedidoService;
import com.levi.service.ProdutoService;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping("/items")
	public List<Item> getAllItems() {
		return itemService.getItems();
	}
	
	@RequestMapping("/items/{idItem}")
	public Item getItem(@PathVariable Integer idItem) {
		return itemService.getItem(idItem);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/items")
	public void addItem(@RequestBody Item item) {
		itemService.addItem(item);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/items/{idItem}")
	public void updateItem(@RequestBody Item item, @PathVariable Integer idItem) {
		itemService.updateItem(item);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/items/{idItem}")
	public void deleteItem(@PathVariable Integer idItem) {
		itemService.deleteItem(idItem);
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
	
	@RequestMapping(method=RequestMethod.POST, value="/items/{idPedido}")
	public void addItemNoPedido(@RequestBody Item item, @PathVariable Integer idPedido) {
		itemService.addItemNoPedido(idPedido, item);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/items/{idProduto}")
	public void addItemNoProduto(@RequestBody Item item, @PathVariable Integer idProduto) {
		itemService.addItemNoProduto(idProduto, item);
	}
}
