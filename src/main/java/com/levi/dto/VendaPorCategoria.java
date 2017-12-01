package com.levi.dto;

import com.levi.auxiliar.Categoria;

public class VendaPorCategoria {

	private Categoria categoria;
	private Double valor;
	
	public VendaPorCategoria(Categoria categoria, Double valor) {
		super();
		this.categoria = categoria;
		this.valor = valor;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
