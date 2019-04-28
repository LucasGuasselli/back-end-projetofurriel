package com.lucasguasselli.projetofurriel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.MilitarDAO;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class MilitarService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private MilitarDAO MilitarDAO;
	
	public Militar buscar(Integer id) {
		Optional<Militar> obj = MilitarDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Militar.class.getName()));
	}
	
	
}
