package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasguasselli.projetofurriel.domain.DespesaAAnular;

public class DespesaAAnularDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;

	private int id;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataFim;
	
	private double valor;
	private int quantidadeDias;
	private String motivo;
	
	public DespesaAAnularDTO() {
		
	}
	
	public DespesaAAnularDTO(DespesaAAnular obj) {
		this.id = obj.getId();
		this.dataInicio = obj.getDataInicio();
		this.dataFim = obj.getDataFim();
		this.valor = obj.getValor();
		this.quantidadeDias = obj.getQuantidadeDias();
		this.motivo = obj.getMotivo();
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
		
}

