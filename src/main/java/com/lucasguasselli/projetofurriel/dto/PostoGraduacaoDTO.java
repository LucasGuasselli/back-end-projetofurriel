package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;

// DTO DATA TRANSFER OBJECT 
public class PostoGraduacaoDTO implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private double soldo;
	private double cotaParte;
	
	public PostoGraduacaoDTO() {
		
	}
	
	public PostoGraduacaoDTO(PostoGraduacao obj) {
		id = obj.getId();
		nome = obj.getNome();
		soldo  = obj.getSoldo();
		cotaParte = obj.getCotaParte();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSoldo() {
		return soldo;
	}

	public void setSoldo(double soldo) {
		this.soldo = soldo;
	}

	public double getCotaParte() {
		return cotaParte;
	}

	public void setCotaParte(double cotaParte) {
		this.cotaParte = cotaParte;
	}
	
	
}
