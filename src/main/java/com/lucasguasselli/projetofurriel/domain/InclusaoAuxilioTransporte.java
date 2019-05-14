package com.lucasguasselli.projetofurriel.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class InclusaoAuxilioTransporte implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;
	
	private double valor;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="aditamento_id")
	private Aditamento aditamento;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="militar_precCP")
	private Militar militar;
	
	public InclusaoAuxilioTransporte() {
		
	}
	
	public InclusaoAuxilioTransporte(Integer id) {
		this.id = id;
	}
	
	public InclusaoAuxilioTransporte(Date dataInicio, double valor) {
		this.dataInicio = dataInicio;
		this.valor = valor;
	}	

	public InclusaoAuxilioTransporte(Date dataInicio, double valor, Aditamento aditamento, Militar militar) {
		super();
		this.dataInicio = dataInicio;
		this.valor = valor;
		this.aditamento = aditamento;
		this.militar = militar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Aditamento getAditamento() {
		return aditamento;
	}

	public void setAditamento(Aditamento aditamento) {
		this.aditamento = aditamento;
	}

	public Militar getMilitar() {
		return militar;
	}

	public void setMilitar(Militar militar) {
		this.militar = militar;
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
		InclusaoAuxilioTransporte other = (InclusaoAuxilioTransporte) obj;
		if (id != other.id)
			return false;
		return true;
	}	
		
}
