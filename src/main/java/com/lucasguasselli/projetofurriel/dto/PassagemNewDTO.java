package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

public class PassagemNewDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;

	private int id;
	private String tipoTransporte;
	private double valor;
	
	public PassagemNewDTO(){
		
	}
	
	public PassagemNewDTO(int id, String tipoTransporte, double valor) {
		super();
		this.id = id;
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
	
	
}
