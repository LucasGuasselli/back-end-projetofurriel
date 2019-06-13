package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

public class ConducaoNewDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;

	private int id;
	private String itinerario;
	private String nomeEmpresa;
	private String tipoDeTransporte;
	private double valor;
	
	private int auxilioTransporteId;
	
	public ConducaoNewDTO() {
		
	}
	
	public ConducaoNewDTO(int id, String itinerario, String nomeEmpresa, String tipoDeTransporte, double valor,
			int auxilioTransporteId) {
		super();
		this.id = id;
		this.itinerario = itinerario;
		this.nomeEmpresa = nomeEmpresa;
		this.tipoDeTransporte = tipoDeTransporte;
		this.valor = valor;
		this.auxilioTransporteId = auxilioTransporteId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItinerario() {
		return itinerario;
	}

	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getTipoDeTransporte() {
		return tipoDeTransporte;
	}

	public void setTipoDeTransporte(String tipoDeTransporte) {
		this.tipoDeTransporte = tipoDeTransporte;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getAuxilioTransporteId() {
		return auxilioTransporteId;
	}

	public void setAuxilioTransporteId(int auxilioTransporteId) {
		this.auxilioTransporteId = auxilioTransporteId;
	}
	
	
}
