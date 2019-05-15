package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExclusaoAuxilioTransporteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
			
	private int id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	private double valor;
	private String motivo;
	
	private int militarPrecCP;
	private int aditamentoId;

	public ExclusaoAuxilioTransporteNewDTO() {
		
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
		
}
