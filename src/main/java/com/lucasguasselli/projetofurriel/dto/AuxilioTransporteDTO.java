package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

import com.lucasguasselli.projetofurriel.domain.AuxilioTransporte;

public class AuxilioTransporteDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	private int id;
	private double valorTotalAT;
	private double valorDiarioAT;
	private boolean exclusao;
	private boolean atualizacao;
	
	public AuxilioTransporteDTO() {
		
	}
	
	public AuxilioTransporteDTO(AuxilioTransporte obj) {
		this.id = obj.getId();
		this.valorTotalAT = obj.getValorTotalAT();
		this.valorDiarioAT = obj.getValorDiarioAT();
		this.exclusao = obj.isExclusao();
		this.atualizacao = obj.isAtualizacao();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
