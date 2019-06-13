package com.lucasguasselli.projetofurriel.services;

import java.util.ArrayList;
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
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + DespesaAAnular.class.getName()));
	}

	public DespesaAAnular searchPrecCPById(Integer id) {
		return despesaDAO.searchPrecCPById(id);
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
			throw new DataIntegrityException("Não é possivel excluir uma Despesa a anular ****");
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
	
	
	// transformando um obj DespesaAAnularNewDTO em obj DespesaAAnular
	public DespesaAAnular fromDTO(DespesaAAnularNewDTO objNewDTO) {
		DespesaAAnular despesa = new DespesaAAnular(objNewDTO.getDataInicio(),objNewDTO.getDataFim(), objNewDTO.getQuantidadeDias(), objNewDTO.getMotivo());
		Militar militar = new Militar(objNewDTO.getMilitarPrecCP());
		Aditamento aditamento = new Aditamento(objNewDTO.getAditamentoId());
		despesa.setMilitar(militar);
		despesa.setAditamento(aditamento);
			return despesa;
	}
	
	// transformando um obj DespesaAAnular em obj DespesaAAnularNewDTO
	public DespesaAAnularNewDTO toNewDTO(DespesaAAnular obj) {
		DespesaAAnularNewDTO despesaNewDTO = new DespesaAAnularNewDTO(
				obj.getId(), obj.getDataInicio(), obj.getDataFim(), 
				obj.getValor(),	obj.getQuantidadeDias(), obj.getMotivo(),
				obj.getMilitar().getPrecCP(), obj.getAditamento().getId());
		
			return despesaNewDTO;
	}
	
	// transformando uma lista de obj DespesaAAnular em uma lista obj DespesaAAnularNewDTO
	public List<DespesaAAnularNewDTO> listToNewDTO(List<DespesaAAnular> lista) {
		List<DespesaAAnularNewDTO> despesasNewDTO = new ArrayList<DespesaAAnularNewDTO>();
		
		for(int i = 0; i < lista.size(); i++) {
			despesasNewDTO.add(new DespesaAAnularNewDTO(
					lista.get(i).getId(), lista.get(i).getDataInicio(), lista.get(i).getDataFim(), 
					lista.get(i).getValor(), lista.get(i).getQuantidadeDias(), lista.get(i).getMotivo(),
					lista.get(i).getMilitar().getPrecCP(), lista.get(i).getAditamento().getId()));
		}				
			return despesasNewDTO;
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
