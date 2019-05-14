package com.lucasguasselli.projetofurriel.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.DespesaDAO;
import com.lucasguasselli.projetofurriel.domain.Aditamento;
import com.lucasguasselli.projetofurriel.domain.DespesaAAnular;
import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.dto.DespesaAAnularDTO;
import com.lucasguasselli.projetofurriel.dto.DespesaAAnularNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class DespesaService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private DespesaDAO despesaDAO;
	
	public DespesaAAnular find(Integer id) {
		Optional<DespesaAAnular> obj = despesaDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}

  	@Transactional
	public DespesaAAnular insert(DespesaAAnular obj) {
		return despesaDAO.save(obj);
	}
	
	public DespesaAAnular update(DespesaAAnular obj) {
		DespesaAAnular newObj = find(obj.getId());
		updateData(newObj, obj);
			return despesaDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			despesaDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Auxilio Transporte ****");
		}
	}
	
	public List<DespesaAAnular> findAll(){
		return despesaDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<DespesaAAnular> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return despesaDAO.findAll(pageRequest);
	}
	
	// a partir de um DTO vai ser construido e retornado um objeto DespesaAAnular
	public DespesaAAnular fromDTO(DespesaAAnularDTO objDTO) {
		return new DespesaAAnular(objDTO.getDataInicio(),objDTO.getDataFim(),objDTO.getQuantidadeDias(), objDTO.getMotivo());
	}
	
	public DespesaAAnular fromDTO(DespesaAAnularNewDTO objNewDTO) {
		DespesaAAnular despesa = new DespesaAAnular(objNewDTO.getDataInicio(),objNewDTO.getDataFim(), objNewDTO.getQuantidadeDias(), objNewDTO.getMotivo());
		Militar militar = new Militar(objNewDTO.getMilitarPrecCP());
		Aditamento aditamento = new Aditamento(objNewDTO.getAditamentoId());
		despesa.setMilitar(militar);
		despesa.setAditamento(aditamento);
			return despesa;
	}	
	
			
	private void updateData(DespesaAAnular newObj, DespesaAAnular obj) {
		newObj.setId(obj.getId());
		newObj.setDataInicio(obj.getDataInicio());
		newObj.setDataFim(obj.getDataFim());
		newObj.setQuantidadeDias(obj.getQuantidadeDias());
		newObj.setValor(obj.getValor());
		newObj.setMotivo(obj.getMotivo());
	}
	
}
