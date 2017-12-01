package com.levi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi.model.Pedido;
import com.levi.model.Usuario;
import com.levi.repository.PedidoRepository;
import com.levi.repository.UsuarioRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Pedido> getPedidos(){
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidoRepository.findAll().forEach(pedidos::add);
		return pedidos;
	}
	
	public Pedido getPedido(Integer idPedido) {
		return pedidoRepository.findOne(idPedido);
	}
	
	public void addPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	public void updatePedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	public void deletePedido(Integer idPedido) {
		pedidoRepository.delete(idPedido);
	}
	
	public List<Pedido> getPedidosDoUsuario(Integer idUsuario) {
		return usuarioRepository.findOne(idUsuario).getPedidos();
	}
	
	public Pedido getPedidoDoUsuario(Integer idPedido) {
		return pedidoRepository.findPedidoDoUsuario(idPedido);
	}

	public void addPedidoNoUsuario(Integer idUsuario, Pedido pedido) {
		Usuario usuario = usuarioRepository.findOne(idUsuario);
		usuario.getPedidos().add(pedido);
		usuarioRepository.save(usuario);
	}
	
}

