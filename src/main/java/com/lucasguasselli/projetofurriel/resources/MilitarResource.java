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

import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.dto.DespesaAAnularNewDTO;
import com.lucasguasselli.projetofurriel.dto.MilitarDTO;
import com.lucasguasselli.projetofurriel.dto.MilitarNewDTO;
import com.lucasguasselli.projetofurriel.resources.utils.URL;
import com.lucasguasselli.projetofurriel.services.MilitarService;

@CrossOrigin
@RestController
@RequestMapping(value="/militares")
public class MilitarResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private MilitarService service;

	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Militar> find(@PathVariable Integer id) {
			Militar obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<MilitarDTO>> findAll() {
			List<Militar> list = service.findAll();
			// percorrendo a lista para declarar o DTO correspondente
			List<MilitarDTO> listDTO = list.stream().map(obj -> new MilitarDTO(obj)).collect(Collectors.toList());
				return ResponseEntity.ok().body(listDTO);	
	}
			
	// retornando um numero X de objetos (pages)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<MilitarDTO>> findPage(
		// @RequestParam serve para tornar os parametros opcionais	
		@RequestParam(value="page", defaultValue="0") Integer page,
		@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
		@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
		@RequestParam(value="direction", defaultValue="ASC")String direction) {
			Page<Militar> list = service.findPage(page,linesPerPage,orderBy, direction);
				// percorrendo a lista para declarar o DTO correspondente
				Page<MilitarDTO> listDTO = list.map(obj -> new MilitarDTO(obj));
					return ResponseEntity.ok().body(listDTO);	
	}
	
	
	@RequestMapping(value="/searchMilitaresByName", method=RequestMethod.GET)
	public ResponseEntity<Page<MilitarDTO>> findMilitarByName(
		// @RequestParam serve para tornar os parametros opcionais	
		@RequestParam(value="nome", defaultValue="0") String nome,
		@RequestParam(value="page", defaultValue="0") Integer page,
		@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
		@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
		@RequestParam(value="direction", defaultValue="ASC")String direction) {
			String nomeDecoded = URL.decodeParam(nome);
			// List<Integer> ids = URL.decodeIntList(postosGraduacoes);
			Page<Militar> list = service.search(nomeDecoded, page,linesPerPage,orderBy, direction);
				// percorrendo a lista para declarar o DTO correspondente
				Page<MilitarDTO> listDTO = list.map(obj -> new MilitarDTO(obj));
					return ResponseEntity.ok().body(listDTO);
			
			
	}
	
	@RequestMapping(value="/searchMilitarByPrecCP", method=RequestMethod.GET)
	public ResponseEntity<MilitarNewDTO> findMilitarByPrecCP(
		// @RequestParam serve para tornar os parametros opcionais	
		@RequestParam(value="precCP", defaultValue="0") int precCP) {
			Militar militar = service.searchMilitarByPrecCP(precCP);
			MilitarNewDTO militarNewDTO = service.toNewDTO(militar);
				return ResponseEntity.ok().body(militarNewDTO);	
	}
			
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody MilitarNewDTO objNewDTO){
			Militar obj = service.fromDTO(objNewDTO);
			obj = service.insert(obj);
		// este metodo serve para enviar o precCP para rota
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getPrecCP()).toUri();
			// created gera o codigo 201 (cadastrado com sucesso)
			return ResponseEntity.created(uri).build();
	}
	
	
	// @PathVariable é utilizado quando o valor da variável é passada diretamente na URL, quando o valor faz parte da url.
	// @Valid valida o Objeto
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MilitarDTO objDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		Militar obj = service.fromDTO(objDTO);
			obj.setPrecCP(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
			return ResponseEntity.noContent().build();
	}		
		
}
