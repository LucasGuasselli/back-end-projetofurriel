package com.lucasguasselli.projetofurriel.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lucasguasselli.projetofurriel.domain.Militar;

public class MilitarDTO{

	
	private int precCP;
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Length(min = 5, max = 50, message="O tamanho deve ser entre 5 e 50 caracteres")
	private String nome;
	
	public MilitarDTO() {
		
	}
	
	public MilitarDTO(Militar obj) {
		this.precCP = obj.getPrecCP();
		this.nome = obj.getNome();
	}

	public int getPrecCP() {
		return precCP;
	}

	public void setPrecCP(int precCP) {
		this.precCP = precCP;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
