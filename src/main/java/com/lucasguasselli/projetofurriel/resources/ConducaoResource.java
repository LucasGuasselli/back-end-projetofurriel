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

import com.lucasguasselli.projetofurriel.domain.Conducao;
import com.lucasguasselli.projetofurriel.dto.ConducaoDTO;
import com.lucasguasselli.projetofurriel.dto.ConducaoNewDTO;
import com.lucasguasselli.projetofurriel.services.AuxilioTransporteService;
import com.lucasguasselli.projetofurriel.services.ConducaoService;


@RestController
@RequestMapping(value="/conducoes")
public class ConducaoResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private ConducaoService service;
	
	@Autowired
	private AuxilioTransporteService auxilioTransporteService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Conducao> find(@PathVariable Integer id) {
			Conducao obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ConducaoDTO>> findAll() {
		List<Conducao> list = service.findAll();
			// percorrendo a lista para declarar o DTO correspondente
			List<ConducaoDTO> listDTO = list.stream().map(obj -> new ConducaoDTO(obj)).collect(Collectors.toList());
				return ResponseEntity.ok().body(listDTO);	
	}
	
	// retornando um numero X de objetos (pages)
		@RequestMapping(value="/page", method=RequestMethod.GET)
		public ResponseEntity<Page<ConducaoDTO>> findPage(
			// @RequestParam serve para tornar os parametros opcionais	
				@RequestParam(value="page", defaultValue="0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue="valor")String orderBy, 
				@RequestParam(value="direction", defaultValue="DESC")String direction) {
					Page<Conducao> list = service.findPage(page,linesPerPage,orderBy, direction);
						// percorrendo a lista para declarar o DTO correspondente
					Page<ConducaoDTO> listDTO = list.map(obj -> new ConducaoDTO(obj));
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
	public ResponseEntity<Void> update(@Valid @RequestBody ConducaoDTO objDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		Conducao obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}	
    
    // @RequestBody faz o obj ser convertido para JSON automaticamente
 	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody ConducaoNewDTO objNewDTO){
 			Conducao obj = service.fromDTO(objNewDTO);
 			auxilioTransporteService.update(obj, objNewDTO);
 			obj = service.insert(obj);
 		// este metodo serve para enviar o precCP para rota
 			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
 				// created gera o codigo 201 (cadastrado com sucesso)
 				return ResponseEntity.created(uri).build();
 	}			
  
}
