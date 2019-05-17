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

import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.dto.PostoGraduacaoDTO;
import com.lucasguasselli.projetofurriel.services.PostoGraduacaoService;

@CrossOrigin
@RestController
@RequestMapping(value="/postosGraduacoes")
public class PostoGraduacaoResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PostoGraduacaoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PostoGraduacao> find(@PathVariable Integer id) {
			PostoGraduacao obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	// A Entidade PostoGraduacao nao precisa possui um NewDTo pois ela pode ser inserida sem ter relacao com outra entidade
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	// @Valid valida o Objeto
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PostoGraduacaoDTO objDTO){
		// transformando um objeto DTO em um objeto Entity
		PostoGraduacao obj = service.fromDTO(objDTO);	
			obj = service.insert(obj);
		// este metodo serve para enviar o precCP para rota
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
				// created gera o codigo 201 (cadastrado com sucesso)
				return ResponseEntity.created(uri).build();
	}
	
	// @PathVariable é utilizado quando o valor da variável é passada diretamente na URL, quando o valor faz parte da url.
	// @Valid valida o Objeto
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PostoGraduacaoDTO objDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		PostoGraduacao obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PostoGraduacaoDTO>> findAll() {
			List<PostoGraduacao> list = service.findAll();
			// percorrendo a lista para declarar o DTO correspondente
			List<PostoGraduacaoDTO> listDTO = list.stream().map(obj -> new PostoGraduacaoDTO(obj)).collect(Collectors.toList());
				return ResponseEntity.ok().body(listDTO);	
	}
	
	// retornando um numero X de objetos (pages)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PostoGraduacaoDTO>> findPage(
		// @RequestParam serve para tornar os parametros opcionais	
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
				Page<PostoGraduacao> list = service.findPage(page,linesPerPage,orderBy, direction);
					// percorrendo a lista para declarar o DTO correspondente
					Page<PostoGraduacaoDTO> listDTO = list.map(obj -> new PostoGraduacaoDTO(obj));
						return ResponseEntity.ok().body(listDTO);	
		}
}
