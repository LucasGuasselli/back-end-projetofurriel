package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

public class AuxilioTransporteNewDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	private int id;
	private double valorTotalAT;
	private double valorDiarioAT;
	private boolean exclusao;
	private boolean atualizacao;
	private boolean entregaSPP;
	
	private int militarPrecCP;

	
	public AuxilioTransporteNewDTO() {
		
	}	
	
	public AuxilioTransporteNewDTO(int id, double valorTotalAT, double valorDiarioAT,
			boolean exclusao, boolean atualizacao, boolean entregaSPP, int militarPrecCP) {
		super();
		this.id = id;
		this.valorTotalAT = valorTotalAT;
		this.valorDiarioAT = valorDiarioAT;
		this.exclusao = exclusao;
		this.atualizacao = atualizacao;
		this.entregaSPP = entregaSPP;
		this.militarPrecCP = militarPrecCP;
	}

	public int getMilitarPrecCP() {
		return militarPrecCP;
	}

	public void setMilitarPrecCP(int militarPrecCP) {
		this.militarPrecCP = militarPrecCP;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public boolean isEntregaSPP() {
		return entregaSPP;
	}
	public void setEntregaSPP(boolean entregaSPP) {
		this.entregaSPP = entregaSPP;
	}

	public boolean isExclusao() {
		return exclusao;
	}

	public void setExclusao(boolean exclusao) {
		this.exclusao = exclusao;
	}

	public boolean isAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(boolean atualizacao) {
		this.atualizacao = atualizacao;
	}

	public double getValorTotalAT() {
		return valorTotalAT;
	}
	public void setValorTotalAT(double valorTotalAT) {
		this.valorTotalAT = valorTotalAT;
	}
	public double getValorDiarioAT() {
		return valorDiarioAT;
	}
	public void setValorDiarioAT(double valorDiarioAT) {
		this.valorDiarioAT = valorDiarioAT;
	}	
	
	
	
}
