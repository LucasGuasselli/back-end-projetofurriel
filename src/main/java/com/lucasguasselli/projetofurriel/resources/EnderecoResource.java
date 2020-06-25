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

import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.dto.EnderecoDTO;
import com.lucasguasselli.projetofurriel.dto.EnderecoNewDTO;
import com.lucasguasselli.projetofurriel.services.EnderecoService;

@CrossOrigin
@RestController
@RequestMapping(value="/enderecos")
public class EnderecoResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private EnderecoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable Integer id) {
			Endereco obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	
	@RequestMapping(value="/procurarEnderecoPorPrecCP", method=RequestMethod.GET)
	public ResponseEntity<EnderecoDTO> findEnderecoByPrecCP(
		// @RequestParam serve para tornar os parametros opcionais	
		@RequestParam(value="precCP", defaultValue="0") int precCP ){
			Endereco endereco = service.search(precCP);
				EnderecoDTO EnderecoDTO = new EnderecoDTO(endereco);
					return ResponseEntity.ok().body(EnderecoDTO);	
	}
	
	// retornando todos objetos
			@RequestMapping(method=RequestMethod.GET)
			public ResponseEntity<List<EnderecoNewDTO>> findAll() {
					List<Endereco> list = service.findAll();
					List<EnderecoNewDTO> listNewDTO = service.listToNewDTO(list);
					// percorrendo a lista para declarar o DTO correspondente
				//	List<EnderecoNewDTO> listNewDTO = list.stream().map(obj -> new EnderecoNewDTO(obj)).collect(Collectors.toList());
						return ResponseEntity.ok().body(listNewDTO);	
			}
			
	// retornando um numero X de objetos (pages)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<EnderecoDTO>> findPage(
		// @RequestParam serve para tornar os parametros opcionais	
		@RequestParam(value="page", defaultValue="0") Integer page,
		@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
		@RequestParam(value="orderBy", defaultValue="cidade")String orderBy, 
		@RequestParam(value="direction", defaultValue="ASC")String direction) {
			Page<Endereco> list = service.findPage(page,linesPerPage,orderBy, direction);
				// percorrendo a lista para declarar o DTO correspondente
				Page<EnderecoDTO> listDTO = list.map(obj -> new EnderecoDTO(obj));
					return ResponseEntity.ok().body(listDTO);	
	}	
			
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody EnderecoNewDTO objNewDTO){
			Endereco obj = service.fromDTO(objNewDTO);
			obj = service.insert(obj);
		// este metodo serve para enviar o precCP para rota
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
				// created gera o codigo 201 (cadastrado com sucesso)
				return ResponseEntity.created(uri).build();
	}
	
	
	// @PathVariable é utilizado quando o valor da variável é passada diretamente na URL, quando o valor faz parte da url.
	// @Valid valida o Objeto
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EnderecoDTO objDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		Endereco obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}
		
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
			return ResponseEntity.noContent().build();
	}		
		
}
