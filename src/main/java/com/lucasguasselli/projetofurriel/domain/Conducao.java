package com.lucasguasselli.projetofurriel.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conducao implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String itinerario;
	private String nomeEmpresa;
	private String tipoDeTransporte;
	private double valor;
	
	// conexao com auxilioTransporte
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="auxilioTransporte_id")
	private AuxilioTransporte auxilioTransporte;
		
	public Conducao() {
		
	}	
	
	public Conducao(int id) {
		this.id = id;
	}
	
	public Conducao(String itinerario, String nomeEmpresa, String tipoDeTransporte, double valor) {
		super();
		this.itinerario = itinerario;
		this.nomeEmpresa = nomeEmpresa;
		this.tipoDeTransporte = tipoDeTransporte;
		this.valor = valor;
	}



	public Conducao(String itinerario, String nomeEmpresa, String tipoDeTransporte, double valor,
			AuxilioTransporte auxilioTransporte) {
		super();
		this.itinerario = itinerario;
		this.nomeEmpresa = nomeEmpresa;
		this.tipoDeTransporte = tipoDeTransporte;
		this.valor = valor;
		this.auxilioTransporte = auxilioTransporte;
	}

	
		
	public AuxilioTransporte getAuxilioTransporte() {
		return auxilioTransporte;
	}

	public void setAuxilioTransporte(AuxilioTransporte auxilioTransporte) {
		this.auxilioTransporte = auxilioTransporte;
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
		Conducao other = (Conducao) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
