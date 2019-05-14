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

import com.lucasguasselli.projetofurriel.dao.ConducaoDAO;
import com.lucasguasselli.projetofurriel.domain.AuxilioTransporte;
import com.lucasguasselli.projetofurriel.domain.Conducao;
import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.dto.ConducaoDTO;
import com.lucasguasselli.projetofurriel.dto.ConducaoNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class ConducaoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private ConducaoDAO conducaoDAO;
	
	public Conducao find(Integer id) {
		Optional<Conducao> obj = conducaoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}

  	@Transactional
	public Conducao insert(Conducao obj) {
		return conducaoDAO.save(obj);
	}
	
	public Conducao update(Conducao obj) {
		Conducao newObj = find(obj.getId());
		updateData(newObj, obj);
			return conducaoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			conducaoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Auxilio Transporte ****");
		}
	}
	
	public List<Conducao> findAll(){
		return conducaoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<Conducao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return conducaoDAO.findAll(pageRequest);
	}
	
	public Conducao fromDTO(ConducaoNewDTO objDTO) {
		Conducao conducao = new Conducao(objDTO.getItinerario(),objDTO.getNomeEmpresa(),objDTO.getTipoDeTransporte(), objDTO.getValor());
		AuxilioTransporte aux = new AuxilioTransporte(objDTO.getAuxilioTransporteId());
		conducao.setAuxilioTransporte(aux);
			return conducao;
	}	
	
	// a partir de um DTO vai ser construido e retornado um objeto Conducao
	public Conducao fromDTO(ConducaoDTO objDTO) {
		return new Conducao(objDTO.getItinerario(),objDTO.getNomeEmpresa(),objDTO.getTipoDeTransporte(), objDTO.getValor());
	}
		
	private void updateData(Conducao newObj, Conducao obj) {
		newObj.setId(obj.getId());
		newObj.setItinerario(obj.getItinerario());
		newObj.setNomeEmpresa(obj.getNomeEmpresa());
		newObj.setTipoDeTransporte(obj.getTipoDeTransporte());
		newObj.setValor(obj.getValor());
	}
	
}
