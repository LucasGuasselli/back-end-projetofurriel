package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class InclusaoAuxilioTransporteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
			
	private int id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataInicio;
	
	private double valor;
	
	private int militarPrecCP;
	private int aditamentoId;

	public InclusaoAuxilioTransporteNewDTO() {
		
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

	public int getMilitarPrecCP() {
		return militarPrecCP;
	}

	public void setMilitarPrecCP(int militarPrecCP) {
		this.militarPrecCP = militarPrecCP;
	}

	public int getAditamentoId() {
		return aditamentoId;
	}

	public void setAditamentoId(int aditamentoId) {
		this.aditamentoId = aditamentoId;
	}
		
}
