package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasguasselli.projetofurriel.domain.ExclusaoAuxilioTransporte;

public class ExclusaoAuxilioTransporteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private int id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	private double valor;
	private String motivo;
	
	public ExclusaoAuxilioTransporteDTO() {
		
	}
	
	public ExclusaoAuxilioTransporteDTO(ExclusaoAuxilioTransporte obj) {
		this.id = obj.getId();
		this.data = obj.getData();
		this.valor = obj.getValor();
		this.motivo = obj.getMotivo();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
	
}
