package com.lucasguasselli.projetofurriel.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.PostoGraduacaoDAO;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;

@Service
public class PostoGraduacaoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PostoGraduacaoDAO postoGradDAO;
	
	public PostoGraduacao buscar(Integer id) {
		Optional<PostoGraduacao> obj = postoGradDAO.findById(id);
			return obj.orElse(null);
	}
}
