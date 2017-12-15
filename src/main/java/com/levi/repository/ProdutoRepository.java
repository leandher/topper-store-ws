package com.levi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.levi.dto.VendaMensal;
import com.levi.dto.VendaPorCategoria;
import com.levi.dto.VendaProduto;
import com.levi.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

	@Query("SELECT produto.categoria, SUM(item.valorTotal) FROM Pedido AS pedido "
		  +"JOIN pedido.itens AS item JOIN item.produto AS produto GROUP BY produto.categoria") 
	VendaPorCategoria calcularVendaPorCategoria();
	
	@Query("SELECT produto.nome, SUM(item.valorTotal) FROM Pedido AS pedido JOIN pedido.itens AS item "
		   +"JOIN item.produto AS produto  GROUP BY produto.nome") 
	VendaProduto calcularVendaProduto();
	
	@Query("SELECT MONTH(pedido.data), YEAR(pedido.data), SUM(item.valorTotal) FROM Pedido AS pedido "
		   +"JOIN pedido.itens AS item JOIN item.produto AS produto GROUP BY MONTH(pedido.data), YEAR(pedido.data)") 
	VendaMensal calcularVendaMensal();
}
