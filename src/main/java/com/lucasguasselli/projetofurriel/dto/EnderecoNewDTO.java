package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

import com.lucasguasselli.projetofurriel.domain.Endereco;

public class EnderecoNewDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String logradouro;
	private String bairro;
	private String localidade;
	private int numero;
	private String complemento;
	
	private int militarPrecCP;
	
	public EnderecoNewDTO() {
		
	}
	
	public EnderecoNewDTO(Endereco obj) {
		this.id = obj.getId();
		this.logradouro = obj.getLogradouro();
		this.bairro = obj.getBairro();
		this.localidade = obj.getLocalidade();
		this.numero = obj.getNumero();
		this.complemento = obj.getComplemento();
		
		this.militarPrecCP = obj.getMilitar().getPrecCP();
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
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}
