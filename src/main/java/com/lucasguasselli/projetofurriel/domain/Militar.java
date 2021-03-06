package com.lucasguasselli.projetofurriel.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Militar {
			
	@Id
	private int precCP;
	private String nome;
	
	// conexao com postoGraduacao
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="postoGraduacao_id")
	private PostoGraduacao postoGraduacao;	
	
	@OneToOne(mappedBy="militar",cascade=CascadeType.ALL)
	private AuxilioTransporte auxilioTransporte;
	
	@OneToOne(mappedBy="militar", cascade=CascadeType.ALL)
	private Endereco endereco;
	
	@OneToMany(mappedBy="militar", cascade=CascadeType.ALL)
	private List<DespesaAAnular> despesas = new ArrayList<>();
	
	@OneToMany(mappedBy="militar", cascade=CascadeType.ALL)
	private List<InclusaoAuxilioTransporte> inclusoes = new ArrayList<>();
	
	@OneToMany(mappedBy="militar", cascade=CascadeType.ALL)
	private List<ExclusaoAuxilioTransporte> exclusoes = new ArrayList<>();
	
	@OneToMany(mappedBy="militar", cascade=CascadeType.ALL)
	private List<SaqueAtrasado> pagamentosAtrasados = new ArrayList<>();
	
	@OneToMany(mappedBy="militar", cascade=CascadeType.ALL)
	private List<AlteracaoValorPassagem> alteracoesValoresPassagens = new ArrayList<>();
	
	public Militar(){
		
	}
	
	public Militar(int precCP) {
		this.precCP = precCP;
	}

	public Militar(int precCP, String nome) {
		super();
		this.precCP = precCP;
		this.nome = nome;
	}	
	
	public Militar(int precCP, String nome, PostoGraduacao postoGraduacao) {
		super();
		this.precCP = precCP;
		this.nome = nome;
		this.postoGraduacao = postoGraduacao;
	}

	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public AuxilioTransporte getAuxilioTransporte() {
		return auxilioTransporte;
	}

	public void setAuxilioTransporte(AuxilioTransporte auxilioTransporte) {
		this.auxilioTransporte = auxilioTransporte;
	}

	public int getPrecCP() {
		return precCP;
	}

	public void setPrecCP(int precCP) {
		this.precCP = precCP;
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PostoGraduacao getPostoGraduacao() {
		return postoGraduacao;
	}

	public void setPostoGraduacao(PostoGraduacao postoGraduacao) {
		this.postoGraduacao = postoGraduacao;
	}

	public List<DespesaAAnular> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<DespesaAAnular> despesas) {
		this.despesas = despesas;
	}

	public List<InclusaoAuxilioTransporte> getInclusoes() {
		return inclusoes;
	}

	public void setInclusoes(List<InclusaoAuxilioTransporte> inclusoes) {
		this.inclusoes = inclusoes;
	}

	public List<ExclusaoAuxilioTransporte> getExclusoes() {
		return exclusoes;
	}

	public void setExclusoes(List<ExclusaoAuxilioTransporte> exclusoes) {
		this.exclusoes = exclusoes;
	}

	public List<SaqueAtrasado> getPagamentosAtrasados() {
		return pagamentosAtrasados;
	}

	public void setPagamentosAtrasados(List<SaqueAtrasado> pagamentosAtrasados) {
		this.pagamentosAtrasados = pagamentosAtrasados;
	}

	public List<AlteracaoValorPassagem> getAlteracoesValoresPassagens() {
		return alteracoesValoresPassagens;
	}

	public void setAlteracoesValoresPassagens(List<AlteracaoValorPassagem> alteracoesValoresPassagens) {
		this.alteracoesValoresPassagens = alteracoesValoresPassagens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + precCP;
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
		Militar other = (Militar) obj;
		if (precCP != other.precCP)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Militar [precCP=" + precCP + ", nome=" + nome + "]";
	}	
	
}
