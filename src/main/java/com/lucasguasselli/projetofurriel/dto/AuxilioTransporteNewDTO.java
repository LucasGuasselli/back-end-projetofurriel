package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

public class AuxilioTransporteNewDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	private int id;
	private double valorTotalAT;
	private double valorDiarioAT;
	
	private int militarPrecCP;

	
	public AuxilioTransporteNewDTO() {
		
	}
	
	
	public int getMilitarPrecCP() {
		return militarPrecCP;
	}


	public void setMilitarPrecCP(int militarPrecCP) {
		this.militarPrecCP = militarPrecCP;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValorTotalAT() {
		return valorTotalAT;
	}
	public void setValorTotalAT(double valorTotalAT) {
		this.valorTotalAT = valorTotalAT;
	}
	public double getValorDiarioAT() {
		return valorDiarioAT;
	}
	public void setValorDiarioAT(double valorDiarioAT) {
		this.valorDiarioAT = valorDiarioAT;
	}	
	
	
	
}