package com.lucasguasselli.projetofurriel.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SaqueAtrasado implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	private String mesReferencia;
	private int quantidadeDias;
	private String motivo;
	private double valor;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
		
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="aditamento_id")
	private Aditamento aditamento;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="militar_precCP")
	private Militar militar;

	public SaqueAtrasado() {
		
	}

	public SaqueAtrasado(int id) {
		super();
		this.id = id;
	}

	public SaqueAtrasado(String mesReferencia, int quantidadeDias, String motivo, double valor, Date data) {
		super();
		this.mesReferencia = mesReferencia;
		this.quantidadeDias = quantidadeDias;
		this.motivo = motivo;
		this.valor = valor;
		this.data = data;
	}

	public SaqueAtrasado(String mesReferencia, int quantidadeDias, String motivo, double valor, Date data,
			Aditamento aditamento, Militar militar) {
		super();
		this.mesReferencia = mesReferencia;
		this.quantidadeDias = quantidadeDias;
		this.motivo = motivo;
		this.valor = valor;
		this.data = data;
		this.aditamento = aditamento;
		this.militar = militar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMesReferencia() {
		return mesReferencia;
	}

	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public void setQuantidadeDias(int quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		SaqueAtrasado other = (SaqueAtrasado) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
