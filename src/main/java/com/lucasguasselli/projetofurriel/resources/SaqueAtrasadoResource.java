package com.lucasguasselli.projetofurriel.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

import com.lucasguasselli.projetofurriel.domain.AuxilioTransporte;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.SaqueAtrasado;
import com.lucasguasselli.projetofurriel.dto.SaqueAtrasadoDTO;
import com.lucasguasselli.projetofurriel.dto.SaqueAtrasadoNewDTO;
import com.lucasguasselli.projetofurriel.services.MilitarService;
import com.lucasguasselli.projetofurriel.services.SaqueAtrasadoService;

@CrossOrigin
@RestController
@RequestMapping(value="/saquesAtrasados")
public class SaqueAtrasadoResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private SaqueAtrasadoService service;
	@Autowired
	private MilitarService militarService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<SaqueAtrasado> find(@PathVariable Integer id) {
			SaqueAtrasado obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SaqueAtrasadoNewDTO>> findAll() {
		List<SaqueAtrasado> list = service.findAll();
		List<SaqueAtrasadoNewDTO> pagamentosNewDTO = service.listToNewDTO(list);
			return ResponseEntity.ok().body(pagamentosNewDTO);	
	}
	
	@RequestMapping(value="/procurarSaquesAtrasadosPorAditamentoId/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<SaqueAtrasadoNewDTO>> findPagamentosAtrasadosByAditamentoId(@PathVariable Integer id){
			List<SaqueAtrasado> list = service.findAll();
			List<SaqueAtrasadoNewDTO> listNewDTO = service.listToNewDTO(list);
			
			List<SaqueAtrasadoNewDTO> resultado = new ArrayList<SaqueAtrasadoNewDTO>();
				for (int i = 0; i < listNewDTO.size(); i++) {
					if (listNewDTO.get(i).getAditamentoId() == id ) {
						resultado.add(listNewDTO.get(i));
					}
				}			
					return ResponseEntity.ok().body(resultado);	
	}
	
	// retornando um numero X de objetos (pages)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<SaqueAtrasadoDTO>> findPage(
		// @RequestParam serve para tornar os parametros opcionais	
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="data")String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC")String direction) {
				Page<SaqueAtrasado> list = service.findPage(page,linesPerPage,orderBy, direction);
				// percorrendo a lista para declarar o DTO correspondente
				Page<SaqueAtrasadoDTO> listDTO = list.map(obj -> new SaqueAtrasadoDTO(obj));
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
	public ResponseEntity<Void> update(@Valid @RequestBody SaqueAtrasadoDTO objDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		SaqueAtrasado obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}
	
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody SaqueAtrasadoNewDTO objNewDTO){
		SaqueAtrasado obj = service.fromDTO(objNewDTO);
			obj = service.insert(obj);
			// este metodo serve para enviar o precCP para rota
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
				// created gera o codigo 201 (cadastrado com sucesso)
					return ResponseEntity.created(uri).build();
	}
	
	
	// este metodo e utilizado para o Saque Atrasado dos militares recem incluidos para receber Auxilio Transporte
		@RequestMapping(value="/inserirSaqueAtrasadoInclusao", method=RequestMethod.POST)
		public ResponseEntity<Void> insertSaqueAtrasadoInclusao(@RequestBody SaqueAtrasadoNewDTO objNewDTO){
			// buscando o respectivo auxilio transporte
			Militar militar = militarService.searchMilitarByPrecCP(objNewDTO.getMilitarPrecCP());
				AuxilioTransporte aux = militar.getAuxilioTransporte();
			
			// calcular o valor do auxilioTransporte
			objNewDTO.setValor(objNewDTO.getQuantidadeDias() * aux.getValorDiarioAT());
			
			SaqueAtrasado obj = service.fromDTO(objNewDTO);			
				obj = service.insert(obj);
				
				// este metodo serve para enviar o precCP para rota
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
					// created gera o codigo 201 (cadastrado com sucesso)
						return ResponseEntity.created(uri).build();
		}
}
