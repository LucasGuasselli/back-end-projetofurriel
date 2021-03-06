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

import com.lucasguasselli.projetofurriel.dao.EnderecoDAO;
import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.domain.InclusaoAuxilioTransporte;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.dto.EnderecoDTO;
import com.lucasguasselli.projetofurriel.dto.EnderecoNewDTO;
import com.lucasguasselli.projetofurriel.dto.InclusaoAuxilioTransporteNewDTO;
import com.lucasguasselli.projetofurriel.dto.MilitarNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private EnderecoDAO enderecoDAO;
	
	public Endereco find(Integer id) {
		Optional<Endereco> obj = enderecoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}

	
	public Endereco search(Integer precCP) {
			return enderecoDAO.search(precCP);
	}

  	@Transactional
	public Endereco insert(Endereco obj) {
		return enderecoDAO.save(obj);
	}
	
	public Endereco update(Endereco obj) {
		Endereco newObj = find(obj.getId());
		updateData(newObj, obj);
			return enderecoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		 find(id);
		// Militar militar = militarService.find(obj.getMilitar().getPrecCP());
			// militar.setEndereco(new Endereco());
			 	// militarService.update(militar);
		
		// System.out.println(militar.getNome());
		try {
			enderecoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Endereco ****");
		}
	}
	
	public List<Endereco> findAll(){
		return enderecoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<Endereco> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return enderecoDAO.findAll(pageRequest);
	}
	
	public Endereco fromDTO(EnderecoNewDTO objDTO) {
		Endereco Endereco = new Endereco(objDTO.getLogradouro(),objDTO.getBairro(),objDTO.getLocalidade(),objDTO.getNumero(),objDTO.getComplemento());
		Militar militar = new Militar(objDTO.getMilitarPrecCP());
		Endereco.setMilitar(militar);
			return Endereco;
	}
	
	
	
	// a partir de um DTO vai ser construido e retornado um objeto Endereco
	public Endereco fromDTO(EnderecoDTO objDTO) {
		return new Endereco(objDTO.getLogradouro(), objDTO.getBairro(), objDTO.getLocalidade(), objDTO.getNumero(), objDTO.getComplemento());
	}
	
	// transformando um obj Endereco em obj EnderecoNewDTO
	public EnderecoNewDTO toNewDTO(Endereco obj) {
		EnderecoNewDTO militarNewDTO = new EnderecoNewDTO(obj);
			return militarNewDTO;
	}
	
	// transformando uma lista de obj Endereco em uma lista obj EnderecoNewDTO
	public List<EnderecoNewDTO> listToNewDTO(List<Endereco> list) {
		List<EnderecoNewDTO> enderecosNewDTO = new ArrayList<EnderecoNewDTO>();
		
		for(int i = 0; i < list.size(); i++) {
			enderecosNewDTO.add(new EnderecoNewDTO(list.get(i)));
		}				
			return enderecosNewDTO;
	}
		
	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setId(obj.getId());
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setBairro(obj.getBairro());
		newObj.setLocalidade(obj.getLocalidade());
		newObj.setNumero(obj.getNumero());
		newObj.setComplemento(obj.getComplemento());
	}
	
}
