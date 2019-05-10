package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

import com.lucasguasselli.projetofurriel.domain.Endereco;

public class EnderecoDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L; 

	private int id;
	private String rua;
	private String bairro;
	private String cidade;
	private int numero;
	private String complemento;
	
	
	public EnderecoDTO() {
		
	}
	
	public EnderecoDTO(Endereco obj) {
		this.id = obj.getId();
		this.rua = obj.getRua();
		this.bairro = obj.getBairro();
		this.cidade = obj.getCidade();
		this.numero = obj.getNumero();
		this.complemento = obj.getComplemento();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRua() {
		return rua;
	}


	public void setRua(String rua) {
		this.rua = rua;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
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
