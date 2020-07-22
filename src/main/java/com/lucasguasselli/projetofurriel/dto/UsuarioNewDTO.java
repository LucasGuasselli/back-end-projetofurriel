package com.lucasguasselli.projetofurriel.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	
	@Column(unique=true)
	private String email;
	private String cpf;
	// private int tipo;
	
	@JsonIgnore
	private String senha;
	
	private int postoGraduacaoId;

	public UsuarioNewDTO() {
		
	}
	
	public UsuarioNewDTO(int id, String nome, String email, String cpf, String senha, int postoGraduacaoId) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.postoGraduacaoId = postoGraduacaoId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getPostoGraduacaoId() {
		return postoGraduacaoId;
	}

	public void setPostoGraduacaoId(int postoGraduacaoId) {
		this.postoGraduacaoId = postoGraduacaoId;
	}
	
	
}
