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

import com.lucasguasselli.projetofurriel.dao.ExclusaoDAO;
import com.lucasguasselli.projetofurriel.domain.Aditamento;
import com.lucasguasselli.projetofurriel.domain.ExclusaoAuxilioTransporte;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.dto.ExclusaoAuxilioTransporteDTO;
import com.lucasguasselli.projetofurriel.dto.ExclusaoAuxilioTransporteNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class ExclusaoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private ExclusaoDAO exclusaoDAO;
	
	public ExclusaoAuxilioTransporte find(Integer id) {
		Optional<ExclusaoAuxilioTransporte> obj = exclusaoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + ExclusaoAuxilioTransporte.class.getName()));
	}

  	@Transactional
	public ExclusaoAuxilioTransporte insert(ExclusaoAuxilioTransporte obj) {
		return exclusaoDAO.save(obj);
	}
	
	public ExclusaoAuxilioTransporte update(ExclusaoAuxilioTransporte obj) {
		ExclusaoAuxilioTransporte newObj = find(obj.getId());
		updateData(newObj, obj);
			return exclusaoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			exclusaoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Exclusão de Auxilio Transporte ****");
		}
	}
	
	public List<ExclusaoAuxilioTransporte> findAll(){
		return exclusaoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<ExclusaoAuxilioTransporte> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return exclusaoDAO.findAll(pageRequest);
	}

	
	// a partir de um DTO vai ser construido e retornado um objeto ExclusaoAAnular
	public ExclusaoAuxilioTransporte fromDTO(ExclusaoAuxilioTransporteDTO objDTO) {
		return new ExclusaoAuxilioTransporte(objDTO.getData(), objDTO.getValor(), objDTO.getMotivo());
	}
	
	public ExclusaoAuxilioTransporte fromDTO(ExclusaoAuxilioTransporteNewDTO objNewDTO) {
		ExclusaoAuxilioTransporte exclusao = new ExclusaoAuxilioTransporte(objNewDTO.getData(),objNewDTO.getValor(), objNewDTO.getMotivo());
		Militar militar = new Militar(objNewDTO.getMilitarPrecCP());
		Aditamento aditamento = new Aditamento(objNewDTO.getAditamentoId());
		exclusao.setMilitar(militar);
		exclusao.setAditamento(aditamento);
			return exclusao;
	}	
			
	private void updateData(ExclusaoAuxilioTransporte newObj, ExclusaoAuxilioTransporte obj) {
		newObj.setId(obj.getId());
		newObj.setData(obj.getData());
		newObj.setValor(obj.getValor());
		newObj.setMotivo(obj.getMotivo());
	}
	
}
