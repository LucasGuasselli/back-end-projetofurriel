package com.lucasguasselli.projetofurriel.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostoGraduacao implements Serializable{

	// esta versao a baixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private double soldo;
	private double cotaParte;
	
	public PostoGraduacao() {
		
	}

	public PostoGraduacao(String nome, double soldo, double cotaParte) {
		super();
		// this.id = id;
		this.nome = nome;
		this.soldo = soldo;
		this.cotaParte = cotaParte;
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

	// HashCode e Equals para comparacoes de Objetos
	
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
		PostoGraduacao other = (PostoGraduacao) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	
	
}
