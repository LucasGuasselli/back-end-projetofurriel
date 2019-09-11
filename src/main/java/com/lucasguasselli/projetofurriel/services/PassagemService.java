package com.lucasguasselli.projetofurriel.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.PassagemDAO;
import com.lucasguasselli.projetofurriel.domain.Passagem;
import com.lucasguasselli.projetofurriel.dto.PassagemNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class PassagemService {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PassagemDAO passagemDAO;
	
	public Passagem find(Integer id) {
		Optional<Passagem> obj = passagemDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Passagem.class.getName()));
	}
	
	public List<Passagem> findAll(){
		return passagemDAO.findAll();
	}

  	@Transactional
	public Passagem insert(Passagem obj) {
		return passagemDAO.save(obj);
	}
	/*
	public Passagem update(Passagem obj) {
		Passagem newObj = find(obj.getId());
		updateData(newObj, obj);
			return passagemDAO.save(newObj);
	}
	
	private void updateData(Conducao newObj, Conducao obj) {
		newObj.setId(obj.getId());
		newObj.setItinerario(obj.getItinerario());
		newObj.setNomeEmpresa(obj.getNomeEmpresa());
		newObj.setTipoDeTransporte(obj.getTipoDeTransporte());
		newObj.setValor(obj.getValor());
	}
	*/
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			passagemDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Passagem ****");
		}
	}
	
	public Passagem fromDTO(PassagemNewDTO objDTO) {
		Passagem passagem = new Passagem(objDTO.getTipoTransporte(), objDTO.getValor());
			return passagem;
	}	
}
