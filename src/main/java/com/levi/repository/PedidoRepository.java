package com.levi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.levi.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

	 @Query("SELECT p FROM Pedido p JOIN p.usuario u WHERE p.idPedido =:idPedido") 
	 Pedido findPedidoDoUsuario(@Param("idPedido") Integer idPedido);
	 
}
