package com.levi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi.model.Item;
import com.levi.model.Pedido;
import com.levi.model.Produto;
import com.levi.repository.ItemRepository;
import com.levi.repository.PedidoRepository;
import com.levi.repository.ProdutoRepository;

@Service
public class ItemService {

	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Item> getItems(){
		List<Item> itens = new ArrayList<Item>();
		itemRepository.findAll().forEach(itens::add);
		return itens;
	}
	
	public Item getItem(Integer idItem) {
		return itemRepository.findOne(idItem);
	}
	
	public void addItem(Item item) {
		itemRepository.save(item);
	}

	public void updateItem(Item item) {
		itemRepository.save(item);
	}

	public void deleteItem(Integer idItem) {
		itemRepository.delete(idItem);
	}
	
	public List<Item> getItensDoPedido(Integer idPedido) {
		return pedidoRepository.findOne(idPedido).getItens();
	}
	
	public Item getItemDoPedido(Integer idItem) {
		return itemRepository.findItemDoPedido(idItem);
	}
	
	public void addItemNoPedido(Integer idPedido, Item item) {
		Pedido pedido = pedidoRepository.findOne(idPedido);
		pedido.getItens().add(item);
		pedidoRepository.save(pedido);
	}
	
	public List<Item> getItensDoProduto(Integer idProduto) {
		return produtoRepository.findOne(idProduto).getItens();
	}
	
	public Item getItemDoProduto(Integer idItem) {
		return itemRepository.findItemDoProduto(idItem);
	}
	
	public void addItemNoProduto(Integer idProduto, Item item) {
		Produto produto = produtoRepository.findOne(idProduto);
		produto.getItens().add(item);
		produtoRepository.save(produto);
	}
}


