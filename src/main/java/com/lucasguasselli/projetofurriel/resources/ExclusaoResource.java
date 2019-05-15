package com.lucasguasselli.projetofurriel.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasguasselli.projetofurriel.domain.ExclusaoAuxilioTransporte;
import com.lucasguasselli.projetofurriel.dto.ExclusaoAuxilioTransporteDTO;
import com.lucasguasselli.projetofurriel.dto.ExclusaoAuxilioTransporteNewDTO;
import com.lucasguasselli.projetofurriel.services.ExclusaoService;


@RestController
@RequestMapping(value="/exclusoesAuxiliosTransporte")
public class ExclusaoResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private ExclusaoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ExclusaoAuxilioTransporte> find(@PathVariable Integer id) {
			ExclusaoAuxilioTransporte obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ExclusaoAuxilioTransporteDTO>> findAll() {
		List<ExclusaoAuxilioTransporte> list = service.findAll();
			// percorrendo a lista para declarar o DTO correspondente
			List<ExclusaoAuxilioTransporteDTO> listDTO = list.stream().map(obj -> new ExclusaoAuxilioTransporteDTO(obj)).collect(Collectors.toList());
				return ResponseEntity.ok().body(listDTO);	
	}
	
	// retornando um numero X de objetos (pages)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ExclusaoAuxilioTransporteDTO>> findPage(
		// @RequestParam serve para tornar os parametros opcionais	
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="data")String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC")String direction) {
				Page<ExclusaoAuxilioTransporte> list = service.findPage(page,linesPerPage,orderBy, direction);
				// percorrendo a lista para declarar o DTO correspondente
				Page<ExclusaoAuxilioTransporteDTO> listDTO = list.map(obj -> new ExclusaoAuxilioTransporteDTO(obj));
					return ResponseEntity.ok().body(listDTO);	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	// @PathVariable é utilizado quando o valor da variável é passada diretamente na URL, quando o valor faz parte da url.
	// @Valid valida o Objeto
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ExclusaoAuxilioTransporteDTO objDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		ExclusaoAuxilioTransporte obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}
	
	
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ExclusaoAuxilioTransporteNewDTO objNewDTO){
		ExclusaoAuxilioTransporte obj = service.fromDTO(objNewDTO);
			obj = service.insert(obj);
		// este metodo serve para enviar o precCP para rota
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			// created gera o codigo 201 (cadastrado com sucesso)
				return ResponseEntity.created(uri).build();
	}
		
}
