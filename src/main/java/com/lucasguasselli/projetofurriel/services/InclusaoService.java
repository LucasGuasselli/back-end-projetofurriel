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

import com.lucasguasselli.projetofurriel.dao.InclusaoDAO;
import com.lucasguasselli.projetofurriel.domain.Aditamento;
import com.lucasguasselli.projetofurriel.domain.InclusaoAuxilioTransporte;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.dto.DespesaAAnularNewDTO;
import com.lucasguasselli.projetofurriel.dto.InclusaoAuxilioTransporteDTO;
import com.lucasguasselli.projetofurriel.dto.InclusaoAuxilioTransporteNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class InclusaoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private InclusaoDAO inclusaoDAO;
	
	public InclusaoAuxilioTransporte find(Integer id) {
		Optional<InclusaoAuxilioTransporte> obj = inclusaoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + InclusaoAuxilioTransporte.class.getName()));
	}

  	@Transactional
	public InclusaoAuxilioTransporte insert(InclusaoAuxilioTransporte obj) {
		return inclusaoDAO.save(obj);
	}
	
	public InclusaoAuxilioTransporte update(InclusaoAuxilioTransporte obj) {
		InclusaoAuxilioTransporte newObj = find(obj.getId());
		updateData(newObj, obj);
			return inclusaoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			inclusaoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Inclusão de Auxilio Transporte ****");
		}
	}
	
	public List<InclusaoAuxilioTransporte> findAll(){
		return inclusaoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<InclusaoAuxilioTransporte> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return inclusaoDAO.findAll(pageRequest);
	}
	
	// a partir de um DTO vai ser construido e retornado um objeto inclusaoAAnular
	public InclusaoAuxilioTransporte fromDTO(InclusaoAuxilioTransporteDTO objDTO) {
		return new InclusaoAuxilioTransporte(objDTO.getDataInicio(),objDTO.getValor());
	}
	
	public InclusaoAuxilioTransporte fromDTO(InclusaoAuxilioTransporteNewDTO objNewDTO) {
		InclusaoAuxilioTransporte inclusao = new InclusaoAuxilioTransporte(objNewDTO.getDataInicio(),objNewDTO.getValor());
		Militar militar = new Militar(objNewDTO.getMilitarPrecCP());
		Aditamento aditamento = new Aditamento(objNewDTO.getAditamentoId());
		inclusao.setMilitar(militar);
		inclusao.setAditamento(aditamento);
			return inclusao;
	}	
			
	private void updateData(InclusaoAuxilioTransporte newObj, InclusaoAuxilioTransporte obj) {
		newObj.setId(obj.getId());
		newObj.setDataInicio(obj.getDataInicio());
		newObj.setValor(obj.getValor());
	}
	
	// transformando uma lista de obj InclusaoAuxilioTransporte em uma lista obj InclusaoAuxilioTransporte
	public List<InclusaoAuxilioTransporteNewDTO> listToNewDTO(List<InclusaoAuxilioTransporte> lista) {
		List<InclusaoAuxilioTransporteNewDTO> inclusoesNewDTO = new ArrayList<InclusaoAuxilioTransporteNewDTO>();
		
		for(int i = 0; i < lista.size(); i++) {
			inclusoesNewDTO.add(new InclusaoAuxilioTransporteNewDTO(
				lista.get(i).getId(), lista.get(i).getDataInicio(), lista.get(i).getValor(),
				lista.get(i).getMilitar().getPrecCP(), lista.get(i).getAditamento().getId()));
		}				
			return inclusoesNewDTO;
	}
	
}
