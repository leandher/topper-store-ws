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

import com.levi.model.Pedido;
import com.levi.service.PedidoService;

@Transactional
@CrossOrigin("*")
@RequestMapping("/api/pedidos")
@RestController
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pedido> getAllPedidos() {
		return pedidoService.getPedidos();
	}

	@RequestMapping(value = "/{idPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Pedido getPedido(@PathVariable Integer idPedido) {
		return pedidoService.getPedido(idPedido);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido) {
		try {
			pedidoService.addPedido(pedido);
			return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<Pedido>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{idPedido}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> updatePedido(
			@RequestBody Pedido pedido, @PathVariable Integer idPedido) {

		try {
			pedidoService.updatePedido(pedido);
			return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<Pedido>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{idPedido}")
	public ResponseEntity<Pedido> deletePedido(
			@PathVariable Integer idPedido) {
		pedidoService.deletePedido(idPedido);
		return new ResponseEntity<Pedido>(HttpStatus.OK);
	}
	
	@RequestMapping("/pedidosDoUsuario/{idUsuario}")
	public List<Pedido> getAllPedidosDoUsuario(@PathVariable Integer idUsuario) {
		return pedidoService.getPedidosDoUsuario(idUsuario);
	}
	
	@RequestMapping("/pedidoDoUsuario/{idPedido}")
	public Pedido getAllPedidos(@PathVariable Integer idPedido) {
		return pedidoService.getPedidoDoUsuario(idPedido);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/pedidos/{idUsuario}")
	public void addPedido(@RequestBody Pedido pedido, @PathVariable Integer idUsuario) {
		pedidoService.addPedidoNoUsuario(idUsuario, pedido);
	}
}
