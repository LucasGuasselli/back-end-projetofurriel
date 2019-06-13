package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PagamentoAtrasadoNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String mesReferencia;
	private int quantidadeDias;
	private String motivo;
	private double valor;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	private int militarPrecCP;
	private int aditamentoId;
	
	public PagamentoAtrasadoNewDTO() {
		
	}
	
	public PagamentoAtrasadoNewDTO(int id, String mesReferencia, int quantidadeDias, String motivo, double valor,
			 int militarPrecCP, int aditamentoId) {
		super();
		this.id = id;
		this.mesReferencia = mesReferencia;
		this.quantidadeDias = quantidadeDias;
		this.motivo = motivo;
		this.valor = valor;
		this.militarPrecCP = militarPrecCP;
		this.aditamentoId = aditamentoId;
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
