package com.lucasguasselli.projetofurriel.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.services.PostoGraduacaoService;

@RestController
@RequestMapping(value="/postosGraduacoes")
public class PostoGraduacaoResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PostoGraduacaoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
			PostoGraduacao obj = service.buscar(id);
				return ResponseEntity.ok().body(obj);	
	}
}
