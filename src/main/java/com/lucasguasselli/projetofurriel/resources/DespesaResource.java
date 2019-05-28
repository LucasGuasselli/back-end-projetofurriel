package com.lucasguasselli.projetofurriel.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasguasselli.projetofurriel.domain.DespesaAAnular;
import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.dto.DespesaAAnularDTO;
import com.lucasguasselli.projetofurriel.dto.DespesaAAnularNewDTO;
import com.lucasguasselli.projetofurriel.dto.EnderecoDTO;
import com.lucasguasselli.projetofurriel.services.DespesaService;

@CrossOrigin
@RestController
@RequestMapping(value="/despesas")
public class DespesaResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private DespesaService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<DespesaAAnular> find(@PathVariable Integer id) {
			DespesaAAnular obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<DespesaAAnularNewDTO>> findAll() {
		List<DespesaAAnular> list = service.findAll();
		List<DespesaAAnularNewDTO> listNewDTO = service.listToNewDTO(list);
			// percorrendo a lista para declarar o DTO correspondente
			//List<DespesaAAnularDTO> listDTO = list.stream().map(obj -> new DespesaAAnularDTO(obj)).collect(Collectors.toList());
				return ResponseEntity.ok().body(listNewDTO);	
	}
	
	// retornando um numero X de objetos (pages)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<DespesaAAnularDTO>> findPage(
		// @RequestParam serve para tornar os parametros opcionais	
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="motivo")String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC")String direction) {
				Page<DespesaAAnular> list = service.findPage(page,linesPerPage,orderBy, direction);
				// percorrendo a lista para declarar o DTO correspondente
				Page<DespesaAAnularDTO> listDTO = list.map(obj -> new DespesaAAnularDTO(obj));
					return ResponseEntity.ok().body(listDTO);	
	}
		
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/searchPrecCPById", method=RequestMethod.GET)
	public ResponseEntity<DespesaAAnularNewDTO> findPrecCPById(
		@RequestParam(value="id", defaultValue="0") int id ){
			DespesaAAnular despesa = service.searchPrecCPById(id);
			DespesaAAnularNewDTO despesaNewDTO = service.toNewDTO(despesa);
				return ResponseEntity.ok().body(despesaNewDTO);	
	}
	
	// @PathVariable é utilizado quando o valor da variável é passada diretamente na URL, quando o valor faz parte da url.
	// @Valid valida o Objeto
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody DespesaAAnularNewDTO objNewDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		DespesaAAnular obj = service.fromDTO(objNewDTO);
			obj.setId(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}
			
	
	 // @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody DespesaAAnularNewDTO objNewDTO){
 		DespesaAAnular obj = service.fromDTO(objNewDTO);
 		obj = service.insert(obj);
 		// este metodo serve para enviar o precCP para rota
 			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
 				// created gera o codigo 201 (cadastrado com sucesso)
 				return ResponseEntity.created(uri).build();
	 }	
}
