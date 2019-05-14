package com.lucasguasselli.projetofurriel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Aditamento implements Serializable{

	// este serialVersion abaixo se faz necessaria por causa do Serializable (padrao java)
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	private String nome;
	private Boolean assinatura = false;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date data;
	
	@OneToMany(mappedBy="aditamento", cascade=CascadeType.ALL)
	private List<DespesaAAnular> descontos = new ArrayList<>();
	
	public Aditamento() {
		
	}
	
	public Aditamento (int id) {
		this.id = id;
	}

	public Aditamento(String nome, Date data) {
		super();
		this.nome = nome;
		this.data = data;
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
	
	public List<DespesaAAnular> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<DespesaAAnular> descontos) {
		this.descontos = descontos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Aditamento other = (Aditamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
