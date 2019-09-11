package com.lucasguasselli.projetofurriel.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasguasselli.projetofurriel.domain.Passagem;
import com.lucasguasselli.projetofurriel.dto.PassagemDTO;
import com.lucasguasselli.projetofurriel.dto.PassagemNewDTO;
import com.lucasguasselli.projetofurriel.services.PassagemService;

@CrossOrigin
@RestController
@RequestMapping(value="/passagens")
public class PassagemResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PassagemService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Passagem> find(@PathVariable Integer id) {
			Passagem obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PassagemDTO>> findAll() {
		List<Passagem> list = service.findAll();
			// percorrendo a lista para declarar o DTO correspondente
			List<PassagemDTO> listDTO = list.stream().map(obj -> new PassagemDTO(obj)).collect(Collectors.toList());
				return ResponseEntity.ok().body(listDTO);	
	}
	
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PassagemNewDTO objNewDTO){
		Passagem obj = service.fromDTO(objNewDTO);
		obj = service.insert(obj);	
			
		// este metodo serve para enviar a chave primaria para rota
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			// created gera o codigo 201 (cadastrado com sucesso)
			return ResponseEntity.created(uri).build();
	 	}	
	 	
	/*
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	// @PathVariable é utilizado quando o valor da variável é passada diretamente na URL, quando o valor faz parte da url.
	// @Valid valida o Objeto
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ConducaoNewDTO objNewDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		Conducao obj = service.fromDTO(objNewDTO);
			obj.setId(id);
			obj = service.update(obj);
			
			auxilioTransporteService.update(objNewDTO);
			alteracaoValorPassagemService.update(objNewDTO);
			
				return ResponseEntity.noContent().build();
	}	
    
    
 	*/
}
