package com.levi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.levi.model.Item;


public interface ItemRepository extends CrudRepository<Item, Integer> {

	
	@Query("SELECT item FROM Pedido AS p JOIN p.itens AS item WHERE item.idItem =:idItem") 
	Item findItemDoPedido(@Param("idItem") Integer idItem);
	
	@Query("SELECT item FROM Produto AS p JOIN p.itens AS item WHERE item.idItem =:idItem") 
	Item findItemDoProduto(@Param("idItem") Integer idItem);
	
	
}
