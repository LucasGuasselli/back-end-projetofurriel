package com.lucasguasselli.projetofurriel.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Militar {

	@Id
	private int precCP;
	private String nome;

	//private int idPosto;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="postoGraduacao_id")
	private PostoGraduacao postoGraduacao = new PostoGraduacao();
	
	public Militar(){
		
	}

	public Militar(int precCP, String nome) {
		super();
		this.precCP = precCP;
		// this.idPosto = idPosto;
		this.nome = nome;
		//this.postoGraduacao = postoGraduacao;
	}

	public int getPrecCP() {
		return precCP;
	}

	public void setPrecCP(int precCP) {
		this.precCP = precCP;
	}
/*
	public int getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(int idPosto) {
		this.idPosto = idPosto;
	}
*/
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
	
	
}
