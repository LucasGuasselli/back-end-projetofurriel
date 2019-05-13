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
	
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	private Boolean assinatura = false;
	
	public AditamentoDTO() {
		
	}
	
	public AditamentoDTO(Aditamento obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.data = obj.getData();
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
	
	
}
