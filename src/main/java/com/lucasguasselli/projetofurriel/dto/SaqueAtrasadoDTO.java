package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasguasselli.projetofurriel.domain.SaqueAtrasado;

public class SaqueAtrasadoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String mesReferencia;
	private int quantidadeDias;
	private String motivo;
	private double valor;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;

	public SaqueAtrasadoDTO() {
		
	}
	
	public SaqueAtrasadoDTO(SaqueAtrasado obj) {
		this.id = obj.getId();
		this.mesReferencia = obj.getMesReferencia();
		this.quantidadeDias = obj.getQuantidadeDias();
		this.motivo = obj.getMotivo();
		this.valor = obj.getValor();
		this.data = obj.getData();
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
		
}
