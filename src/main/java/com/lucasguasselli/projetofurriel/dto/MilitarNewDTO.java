package com.lucasguasselli.projetofurriel.dto;

public class MilitarNewDTO {

	private int precCP;
	private String nome;
	
	private int postoGraduacaoId;
	
	public MilitarNewDTO() {
		
	}
	
	public MilitarNewDTO(int precCP, String nome, int postoGraduacaoId) {
		super();
		this.precCP = precCP;
		this.nome = nome;
		this.postoGraduacaoId = postoGraduacaoId;
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

	public int getPostoGraduacaoId() {
		return postoGraduacaoId;
	}

	public void setPostoGraduacaoId(int postoGraduacaoId) {
		this.postoGraduacaoId = postoGraduacaoId;
	}
	 
	
}
