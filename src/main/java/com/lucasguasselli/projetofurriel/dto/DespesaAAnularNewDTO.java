package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DespesaAAnularNewDTO implements Serializable{

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
	
	private int militarPrecCP;
	private int aditamentoId;
	
	public DespesaAAnularNewDTO() {
		
	}
	
	public DespesaAAnularNewDTO(int id, Date dataInicio, Date dataFim, double valor, int quantidadeDias, String motivo,
			int militarPrecCP, int aditamentoId) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valor = valor;
		this.quantidadeDias = quantidadeDias;
		this.motivo = motivo;
		this.militarPrecCP = militarPrecCP;
		this.aditamentoId = aditamentoId;
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
