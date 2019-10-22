package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasguasselli.projetofurriel.domain.Aditamento;

public class AditamentoDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;	
	private Boolean assinatura = false;
	private String despesaPeriodo;
	private String exclusaoTexto;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	public AditamentoDTO() {
		
	}
	
	public AditamentoDTO(Aditamento obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.data = obj.getData();
		this.despesaPeriodo = obj.getDespesaPeriodo();
		this.exclusaoTexto = obj.getExclusaoTexto();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Boolean assinatura) {
		this.assinatura = assinatura;
	}

	public String getDespesaPeriodo() {
		return despesaPeriodo;
	}

	public void setDespesaPeriodo(String despesaPeriodo) {
		this.despesaPeriodo = despesaPeriodo;
	}

	public String getExclusaoTexto() {
		return exclusaoTexto;
	}

	public void setExclusaoTexto(String exclusaoTexto) {
		this.exclusaoTexto = exclusaoTexto;
	}	
	
}
