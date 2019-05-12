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

import com.lucasguasselli.projetofurriel.dao.AuxilioTransporteDAO;
import com.lucasguasselli.projetofurriel.domain.AuxilioTransporte;
import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.dto.AuxilioTransporteDTO;
import com.lucasguasselli.projetofurriel.dto.AuxilioTransporteNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class AuxilioTransporteService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private AuxilioTransporteDAO auxilioTransporteDAO;
	
	public AuxilioTransporte find(Integer id) {
		Optional<AuxilioTransporte> obj = auxilioTransporteDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}

  	@Transactional
	public AuxilioTransporte insert(AuxilioTransporte obj) {
		return auxilioTransporteDAO.save(obj);
	}
	
	public AuxilioTransporte update(AuxilioTransporte obj) {
		AuxilioTransporte newObj = find(obj.getId());
		updateData(newObj, obj);
			return auxilioTransporteDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			auxilioTransporteDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Auxilio Transporte ****");
		}
	}
	
	public List<AuxilioTransporte> findAll(){
		return auxilioTransporteDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<AuxilioTransporte> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return auxilioTransporteDAO.findAll(pageRequest);
	}
	
/*
	// buscando por nome
	public Page<Endereco> search(String nome, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		// List<PostoGraduacao> postosGraduacoes = postoGraduacaoDAO.findAllById(ids);
			return EnderecoDAO.search(nome,pageRequest);
	}
		
*/
	
	public AuxilioTransporte fromDTO(AuxilioTransporteNewDTO objDTO) {
		Militar militar = new Militar(objDTO.getMilitarPrecCP());
		AuxilioTransporte auxilioTransporte = new AuxilioTransporte(objDTO.getValorTotalAT(),objDTO.getValorDiarioAT(), militar);
			return auxilioTransporte;
	}	
	
	// a partir de um DTO vai ser construido e retornado um objeto Endereco
	public AuxilioTransporte fromDTO(AuxilioTransporteDTO objDTO) {
		return new AuxilioTransporte(objDTO.getValorTotalAT(),objDTO.getValorDiarioAT());
	}
		
	private void updateData(AuxilioTransporte newObj, AuxilioTransporte obj) {
		newObj.setId(obj.getId());
		newObj.setValorTotalAT(obj.getValorTotalAT());
		newObj.setValorDiarioAT(obj.getValorDiarioAT());		
	}
	
}
