package com.lucasguasselli.projetofurriel.resources;

import java.net.URI;
import java.util.ArrayList;
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

import com.lucasguasselli.projetofurriel.domain.Conducao;
import com.lucasguasselli.projetofurriel.dto.ConducaoDTO;
import com.lucasguasselli.projetofurriel.dto.ConducaoNewDTO;
import com.lucasguasselli.projetofurriel.services.AlteracaoValorPassagemService;
import com.lucasguasselli.projetofurriel.services.AuxilioTransporteService;
import com.lucasguasselli.projetofurriel.services.ConducaoService;
import com.lucasguasselli.projetofurriel.services.InclusaoService;

@CrossOrigin
@RestController
@RequestMapping(value="/conducoes")
public class ConducaoResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private ConducaoService service;
	
	@Autowired
	private AuxilioTransporteService auxilioTransporteService;
	@Autowired
	private AlteracaoValorPassagemService alteracaoValorPassagemService;
	@Autowired
	private InclusaoService inclusaoService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Conducao> find(@PathVariable Integer id) {
			Conducao obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	@RequestMapping(value="/procurarConducoesPorAuxilioTransporteId/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ConducaoNewDTO>> findConducoesByAuxilioTransporteId(@PathVariable Integer id) {
			List<Conducao> list = service.findAll();
			List<ConducaoNewDTO> listNewDTO = service.listToNewDTO(list);
			List<ConducaoNewDTO> resultado = new ArrayList<ConducaoNewDTO>();
			for(int i = 0; i < listNewDTO.size(); i++) {
				if (listNewDTO.get(i).getAuxilioTransporteId() == id) {
					resultado.add(listNewDTO.get(i));
				}
			}
				return ResponseEntity.ok().body(resultado);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ConducaoDTO>> findAll() {
		List<Conducao> list = service.findAll();
			// percorrendo a lista para declarar o DTO correspondente
			List<ConducaoDTO> listDTO = list.stream().map(obj -> new ConducaoDTO(obj)).collect(Collectors.toList());
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
	public ResponseEntity<Void> update(@Valid @RequestBody ConducaoNewDTO objNewDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		Conducao obj = service.fromDTO(objNewDTO);
			obj.setId(id);
			//atulizando primeiro a conducao
				obj = service.update(obj);
			// atualizando o auxilioTransporte
				auxilioTransporteService.update(objNewDTO);
			// atualizando o valor correto da atuazacaoAuxilioTransporte	
				alteracaoValorPassagemService.update(objNewDTO);			
					return ResponseEntity.noContent().build();
	}	
    
    // USADA PARA CADASTRO AUX TRANSP
    // @RequestBody faz o obj ser convertido para JSON automaticamente
 	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody ConducaoNewDTO objNewDTO){
 		Conducao obj = service.fromDTO(objNewDTO);
 			obj = service.insert(obj);
 			
 		// atualizando o auxilioTransporte
 					auxilioTransporteService.update(objNewDTO);
 		// este metodo serve para enviar o precCP para rota
 			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
 				// created gera o codigo 201 (cadastrado com sucesso)
 				return ResponseEntity.created(uri).build();
 	}			
  
 	// USADA PARA ATUALIZACAO AUX TRANSP
 	 // @RequestBody faz o obj ser convertido para JSON automaticamente
 	@RequestMapping(value="/inserirNovaConducao", method=RequestMethod.POST)
 	public ResponseEntity<Void> insertNewConducao(@RequestBody ConducaoNewDTO objNewDTO){
 		Conducao obj = service.fromDTO(objNewDTO);
 			obj = service.insert(obj);
 		// atualizando o auxilioTransporte
			auxilioTransporteService.update(objNewDTO);
		// atualizando o valor correto da atuazacaoAuxilioTransporte	
			alteracaoValorPassagemService.update(objNewDTO);			
			// este metodo serve para enviar o precCP para rota
 			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
 				// created gera o codigo 201 (cadastrado com sucesso)
 				return ResponseEntity.created(uri).build();
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
 	
}
