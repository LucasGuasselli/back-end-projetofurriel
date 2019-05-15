package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasguasselli.projetofurriel.domain.AlteracaoValorPassagem;

public class AlteracaoValorPassagemDTO implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private int id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;
	private String motivo;
	private double valor;
	
	public AlteracaoValorPassagemDTO() {
		
	}
	
	public AlteracaoValorPassagemDTO(AlteracaoValorPassagem obj) {
		this.id = obj.getId();
		this.dataInicio = obj.getDataInicio();
		this.motivo = obj.getMotivo();
		this.valor = obj.getValor();
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
		
}
