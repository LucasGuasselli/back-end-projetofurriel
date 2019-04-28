package com.lucasguasselli.projetofurriel.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.services.MilitarService;

@RestController
@RequestMapping(value="/militares")
public class MilitarResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private MilitarService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
			Militar obj = service.buscar(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	/*
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Militar obj){
			obj = service.insert(obj);
		// este metodo serve para enviar o precCP para rota
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getPrecCP()).toUri();
				// created gera o codigo 201 (cadastrado com sucesso)
				return ResponseEntity.created(uri).build();
	}
	*/
}
