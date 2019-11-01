package com.lucasguasselli.projetofurriel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AuxilioTransporte implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double valorTotalAT;
	private double valorDiarioAT;
	private boolean exclusao;
	private boolean atualizacao;
	private boolean entregaSPP;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="militar_precCP")
	private Militar militar;	
	
	@OneToMany(mappedBy="auxilioTransporte", cascade=CascadeType.ALL)
	private List<Conducao> conducao = new ArrayList<>();
	
	public AuxilioTransporte() {
		
	}
	
	public AuxilioTransporte(int id) {
		this.id = id;
	}
	
	public AuxilioTransporte(double valorTotalAT, double valorDiarioAT, boolean exclusao, boolean atualizacao, boolean entregaSPP) {
		super();
		this.valorTotalAT = valorTotalAT;
		this.valorDiarioAT = valorDiarioAT;
		this.exclusao = exclusao;
		this.atualizacao = atualizacao;
		this.entregaSPP = entregaSPP;
	} 
		
	public AuxilioTransporte(double valorTotalAT, double valorDiarioAT, boolean exclusao, boolean atualizacao, boolean entregaSPP, Militar militar) {
		super();
		this.valorTotalAT = valorTotalAT;
		this.valorDiarioAT = valorDiarioAT;
		this.exclusao = exclusao;
		this.atualizacao = atualizacao;
		this.militar = militar;
		this.entregaSPP = entregaSPP;
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

	public Militar getMilitar() {
		return militar;
	}

	public void setMilitar(Militar militar) {
		this.militar = militar;
	}

	public List<Conducao> getConducao() {
		return conducao;
	}

	public void setConducao(List<Conducao> conducao) {
		this.conducao = conducao;
	}
	
	public boolean isAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(boolean atualizacao) {
		this.atualizacao = atualizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuxilioTransporte other = (AuxilioTransporte) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AuxilioTransporte [id=" + id + ", valorTotalAT=" + valorTotalAT + ", valorDiarioAT=" + valorDiarioAT
				+ ", militar=" + militar + "]";
	}	
	
}
