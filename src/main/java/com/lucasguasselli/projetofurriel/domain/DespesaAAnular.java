package com.lucasguasselli.projetofurriel.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DespesaAAnular implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataFim;
	
	private double valor;
	private int quantidadeDias;
	private String motivo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="aditamento_id")
	private Aditamento aditamento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="militar_precCP")
	private Militar militar;
	
	public DespesaAAnular() {
		
	}
	
	public DespesaAAnular(int id) {
		this.id = id;
	}

	public DespesaAAnular(Date dataInicio, Date dataFim, int quantidadeDias, double valor, String motivo) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.quantidadeDias = quantidadeDias;
		this.valor = valor;
		this.motivo = motivo;
	}

	public DespesaAAnular(Date dataInicio, Date dataFim,int quantidadeDias, String motivo, Aditamento aditamento,
			Militar militar) {
		super();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.quantidadeDias = quantidadeDias;
		this.motivo = motivo;
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

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public void setQuantidadeDias(int quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DespesaAAnular other = (DespesaAAnular) obj;
		if (id != other.id)
			return false;
		return true;
	}
		
}
