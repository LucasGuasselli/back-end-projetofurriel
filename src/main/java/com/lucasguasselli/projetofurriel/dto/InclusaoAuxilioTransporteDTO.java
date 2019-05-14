package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasguasselli.projetofurriel.domain.InclusaoAuxilioTransporte;

public class InclusaoAuxilioTransporteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private int id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;
	
	private double valor;
	
	public InclusaoAuxilioTransporteDTO() {
		
	}
	
	public InclusaoAuxilioTransporteDTO(InclusaoAuxilioTransporte obj) {
		this.id = obj.getId();
		this.dataInicio = obj.getDataInicio();
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
