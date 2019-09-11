package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

import com.lucasguasselli.projetofurriel.domain.Passagem;

public class PassagemDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String tipoTransporte;
	private double valor;
	
	
	public PassagemDTO() {
		
	}
	
	public PassagemDTO(Passagem obj) {
		this.id = obj.getId();
		this.tipoTransporte = obj.getTipoTransporte();
		this.valor = obj.getValor();
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
		
}
