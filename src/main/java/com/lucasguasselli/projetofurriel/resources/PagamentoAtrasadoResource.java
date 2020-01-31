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
import com.lucasguasselli.projetofurriel.domain.PagamentoAtrasado;
import com.lucasguasselli.projetofurriel.dto.PagamentoAtrasadoDTO;
import com.lucasguasselli.projetofurriel.dto.PagamentoAtrasadoNewDTO;
import com.lucasguasselli.projetofurriel.services.MilitarService;
import com.lucasguasselli.projetofurriel.services.PagamentoAtrasadoService;

@CrossOrigin
@RestController
@RequestMapping(value="/pagamentosAtrasados")
public class PagamentoAtrasadoResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PagamentoAtrasadoService service;
	@Autowired
	private MilitarService militarService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PagamentoAtrasado> find(@PathVariable Integer id) {
			PagamentoAtrasado obj = service.find(id);
				return ResponseEntity.ok().body(obj);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PagamentoAtrasadoNewDTO>> findAll() {
		List<PagamentoAtrasado> list = service.findAll();
		List<PagamentoAtrasadoNewDTO> pagamentosNewDTO = service.listToNewDTO(list);
			return ResponseEntity.ok().body(pagamentosNewDTO);	
	}
	
	@RequestMapping(value="/searchPagamentosAtrasadosByAditamentoId/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<PagamentoAtrasadoNewDTO>> findPagamentosAtrasadosByAditamentoId(@PathVariable Integer id){
			List<PagamentoAtrasado> list = service.findAll();
			List<PagamentoAtrasadoNewDTO> listNewDTO = service.listToNewDTO(list);
			
			List<PagamentoAtrasadoNewDTO> resultado = new ArrayList<PagamentoAtrasadoNewDTO>();
				for (int i = 0; i < listNewDTO.size(); i++) {
					if (listNewDTO.get(i).getAditamentoId() == id ) {
						resultado.add(listNewDTO.get(i));
					}
				}			
					return ResponseEntity.ok().body(resultado);	
	}
	
	// retornando um numero X de objetos (pages)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<PagamentoAtrasadoDTO>> findPage(
		// @RequestParam serve para tornar os parametros opcionais	
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="data")String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC")String direction) {
				Page<PagamentoAtrasado> list = service.findPage(page,linesPerPage,orderBy, direction);
				// percorrendo a lista para declarar o DTO correspondente
				Page<PagamentoAtrasadoDTO> listDTO = list.map(obj -> new PagamentoAtrasadoDTO(obj));
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
	public ResponseEntity<Void> update(@Valid @RequestBody PagamentoAtrasadoDTO objDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity
		PagamentoAtrasado obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}
	
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PagamentoAtrasadoNewDTO objNewDTO){
		PagamentoAtrasado obj = service.fromDTO(objNewDTO);
			obj = service.insert(obj);
			// este metodo serve para enviar o precCP para rota
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
				// created gera o codigo 201 (cadastrado com sucesso)
					return ResponseEntity.created(uri).build();
	}
	
	
	// este metodo e utilizado para o Saque Atrasado dos militares recem incluidos para receber Auxilio Transporte
		@RequestMapping(value="/insertSaqueAtrasadoInclusao", method=RequestMethod.POST)
		public ResponseEntity<Void> insertSaqueAtrasadoInclusao(@RequestBody PagamentoAtrasadoNewDTO objNewDTO){
			// buscando o respectivo auxilio transporte
			Militar militar = militarService.searchMilitarByPrecCP(objNewDTO.getMilitarPrecCP());
				AuxilioTransporte aux = militar.getAuxilioTransporte();
			
			// calcular o valor do auxilioTransporte
			objNewDTO.setValor(objNewDTO.getQuantidadeDias() * aux.getValorDiarioAT());
			
			PagamentoAtrasado obj = service.fromDTO(objNewDTO);			
				obj = service.insert(obj);
				
				// este metodo serve para enviar o precCP para rota
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
					// created gera o codigo 201 (cadastrado com sucesso)
						return ResponseEntity.created(uri).build();
		}
}
