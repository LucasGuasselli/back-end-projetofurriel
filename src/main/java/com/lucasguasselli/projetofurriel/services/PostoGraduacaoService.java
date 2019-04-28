package com.lucasguasselli.projetofurriel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.PostoGraduacaoDAO;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class PostoGraduacaoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PostoGraduacaoDAO postoGraduacaoDAO;
	
	public PostoGraduacao buscar(Integer id) {
		Optional<PostoGraduacao> obj = postoGraduacaoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + PostoGraduacao.class.getName()));
	}
	
	
}
