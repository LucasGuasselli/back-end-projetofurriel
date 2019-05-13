package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

import com.lucasguasselli.projetofurriel.domain.Conducao;

public class ConducaoDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String itinerario;
	private String nomeEmpresa;
	private String tipoDeTransporte;
	private double valor;
	
	public ConducaoDTO() {
		
	}
	
	public ConducaoDTO(Conducao obj) {
		this.id = obj.getId();
		this.itinerario = obj.getItinerario();
		this.nomeEmpresa = obj.getNomeEmpresa();
		this.tipoDeTransporte = obj.getTipoDeTransporte();
		this.valor = obj.getValor();
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
		

}
