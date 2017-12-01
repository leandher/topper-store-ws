package com.levi.dto;

public class VendaMensal {
	
	private Integer mes;
	private Integer ano;
	private Double valor;
	
	public VendaMensal(Integer mes, Integer ano, Double valor) {
		super();
		this.mes = mes;
		this.ano = ano;
		this.valor = valor;
	}
	
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
