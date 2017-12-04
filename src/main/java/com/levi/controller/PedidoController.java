package com.levi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levi.model.Pedido;
import com.levi.service.PedidoService;

@RestController
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping("/api/pedidos")
	public List<Pedido> getAllPedidos() {
		return pedidoService.getPedidos();
	}
	
	@RequestMapping("/api/pedidos/{idPedido}")
	public Pedido getPedido(@PathVariable Integer idPedido) {
		return pedidoService.getPedido(idPedido);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/pedidos")
	public void addPedido(@RequestBody Pedido pedido) {
		pedidoService.addPedido(pedido);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/api/pedidos/{idPedido}")
	public void updatePedido(@RequestBody Pedido pedido, @PathVariable Integer idPedido) {
		pedidoService.updatePedido(pedido);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/api/pedidos/{idPedido}")
	public void deletePedido(@PathVariable Integer idPedido) {
		pedidoService.deletePedido(idPedido);
	}
	
	@RequestMapping("/api/pedidosDoUsuario/{idUsuario}")
	public List<Pedido> getAllPedidosDoUsuario(@PathVariable Integer idUsuario) {
		return pedidoService.getPedidosDoUsuario(idUsuario);
	}
	
	@RequestMapping("/api/pedidoDoUsuario/{idPedido}")
	public Pedido getAllPedidos(@PathVariable Integer idPedido) {
		return pedidoService.getPedidoDoUsuario(idPedido);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/api/pedidos/{idUsuario}")
	public void addPedido(@RequestBody Pedido pedido, @PathVariable Integer idUsuario) {
		pedidoService.addPedidoNoUsuario(idUsuario, pedido);
	}
}
