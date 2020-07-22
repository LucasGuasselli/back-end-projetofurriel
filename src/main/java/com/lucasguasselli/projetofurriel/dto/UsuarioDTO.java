package com.lucasguasselli.projetofurriel.dto;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasguasselli.projetofurriel.domain.Usuario;

public class UsuarioDTO {

	private int id;
	private String nome;
	
	@Column(unique=true)
	private String email;
	private String cpf;
	// private int tipo;
	
	@JsonIgnore
	private String senha;
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Usuario obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.cpf = obj.getCpf();
		this.senha = obj.getSenha();
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
	
	
}
