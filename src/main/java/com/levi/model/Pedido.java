package com.levi.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.levi.auxiliar.Status;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedido;
	
	@Column(name = "numero")
	private Integer numero;
	
	@Column(name = "data")
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name ="id_pedido")
	private List<Item> itens;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	
}
