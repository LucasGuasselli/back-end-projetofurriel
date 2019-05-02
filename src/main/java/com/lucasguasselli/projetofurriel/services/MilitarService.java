package com.lucasguasselli.projetofurriel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.MilitarDAO;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.dto.MilitarDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class MilitarService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private MilitarDAO militarDAO;
	
	public Militar find(Integer precCP) {
		Optional<Militar> obj = militarDAO.findById(precCP);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + precCP + ", Tipo: " + Militar.class.getName()));
	}
/*
	public Militar insert(Militar obj) {
		return MilitarDAO.save(obj);
	}
*/
	
	public Militar update(Militar obj) {
		Militar newObj = find(obj.getPrecCP());
		updateData(newObj, obj);
			return militarDAO.save(newObj);
	}
	
	public void delete(Integer precCP) {
		find(precCP);
		try {
			militarDAO.deleteById(precCP);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Militar que possui auxilio transporte");
		}
	}
	
	public List<Militar> findAll(){
		return militarDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<Militar> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return militarDAO.findAll(pageRequest);
	}
	
	// a partir de um DTO vai ser construido e retornado um objeto Militar
	public Militar fromDTO(MilitarDTO objDTO) {
			return new Militar(objDTO.getPrecCP(),objDTO.getNome());
		}
	
	private void updateData(Militar newObj, Militar obj) {
		newObj.setPrecCP(obj.getPrecCP());
		newObj.setNome(obj.getNome());
	}
	
}
