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
public class AlteracaoValorPassagem implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;
	private String motivo;
	private double valor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="aditamento_id")
	private Aditamento aditamento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="militar_precCP")
	private Militar militar;

	public AlteracaoValorPassagem() {
		
	}

	public AlteracaoValorPassagem(int id) {
		super();
		this.id = id;
	}

	public AlteracaoValorPassagem(Date dataInicio, String motivo, double valor) {
		super();
		this.dataInicio = dataInicio;
		this.motivo = motivo;
		this.valor = valor;
	}

	public AlteracaoValorPassagem(Date dataInicio, String motivo, double valor, Aditamento aditamento,
			Militar militar) {
		super();
		this.dataInicio = dataInicio;
		this.motivo = motivo;
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
	
	
}
