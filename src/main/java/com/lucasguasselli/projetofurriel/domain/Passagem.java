package com.lucasguasselli.projetofurriel.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passagem implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String tipoTransporte;
	private double valor;
	
	public Passagem() {
		
	}	
	
	public Passagem(int id) {
		super();
		this.id = id;
	}

	public Passagem(String tipoTransporte, double valor) {
		super();
		this.tipoTransporte = tipoTransporte;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoTransporte() {
		return tipoTransporte;
	}
	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passagem other = (Passagem) obj;
		if (id != other.id)
			return false;
		return true;
	}
		
}
