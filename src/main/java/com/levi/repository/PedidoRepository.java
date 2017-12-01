package com.levi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.levi.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

	 @Query("SELECT pedido FROM Usuario AS u JOIN u.pedidos AS pedido WHERE pedido.idPedido =:idPedido") 
	 Pedido findPedidoDoUsuario(@Param("idPedido") Integer idPedido);
	 
}
