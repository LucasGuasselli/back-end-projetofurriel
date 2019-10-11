package com.lucasguasselli.projetofurriel.resources;

import java.net.URI;
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
import com.lucasguasselli.projetofurriel.domain.Passagem;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.dto.AuxilioTransporteDTO;
import com.lucasguasselli.projetofurriel.dto.AuxilioTransporteNewDTO;
import com.lucasguasselli.projetofurriel.services.AuxilioTransporteService;
import com.lucasguasselli.projetofurriel.services.MilitarService;
import com.lucasguasselli.projetofurriel.services.PassagemService;
import com.lucasguasselli.projetofurriel.services.PostoGraduacaoService;

@CrossOrigin
@RestController
@RequestMapping(value="/auxiliosTransporte")
public class AuxilioTransporteResource {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private AuxilioTransporteService service;
	@Autowired
	private MilitarService militarService;
	@Autowired 
	private PostoGraduacaoService postoGracuacaoService;
	@Autowired
	private PassagemService passagensService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AuxilioTransporteNewDTO> find(@PathVariable Integer id) {
			AuxilioTransporte obj = service.find(id);
			AuxilioTransporteNewDTO objNewDTO = service.toNewDTO(obj);
				return ResponseEntity.ok().body(objNewDTO);	
	}
	
	// retornando todos objetos
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AuxilioTransporteNewDTO>> findAll() {
		List<AuxilioTransporte> list = service.findAll();
		List<AuxilioTransporteNewDTO> listNewDTO = service.listToNewDTO(list);
			return ResponseEntity.ok().body(listNewDTO);	
	}
	
	// verifica quais auxilios estao desatualizados e atualiza o Banco de Dados 
	// conforme comparacao com o preco das passagenss 
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ResponseEntity<Void> UpdateAll() {
		List<AuxilioTransporte> listAuxilios = service.findAll();
		List<Passagem> listPassagens = passagensService.findAll();
		
			service.update(listAuxilios, listPassagens);
				return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/searchAuxilioTransporteByPrecCP", method=RequestMethod.GET)
	public ResponseEntity<AuxilioTransporte> findAuxilioTransporteByPrecCP(
		// @RequestParam serve para tornar os parametros opcionais	
		@RequestParam(value="precCP", defaultValue="0") int precCP) {
			// encontrando o militar correspondente e atribuindo o Auxilio Transporte
				Militar militar = militarService.searchMilitarByPrecCP(precCP);
					AuxilioTransporte aux = militar.getAuxilioTransporte();
			// AuxilioTransporteNewDTO auxNewDTO = service.toNewDTO(aux);
				return ResponseEntity.ok().body(aux);	
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
			return ResponseEntity.noContent().build();
	}
			
	// @PathVariable é utilizado quando o valor da variável é passada diretamente na URL, quando o valor faz parte da url.
	// @Valid valida o Objeto
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AuxilioTransporteDTO objDTO, @PathVariable Integer id){
		// transformando um objeto DTO em um objeto Entity    	
		AuxilioTransporte obj = service.fromDTO(objDTO);
			obj.setId(id);
			obj = service.update(obj);
				return ResponseEntity.noContent().build();
	}
	
	// @RequestBody faz o obj ser convertido para JSON automaticamente
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody AuxilioTransporteNewDTO objNewDTO){
			AuxilioTransporte obj = service.fromDTO(objNewDTO);
			Militar militar = militarService.find(objNewDTO.getMilitarPrecCP());
			// salvando auxilio transporte com o valor negativo dos 6 % da cota parte
				PostoGraduacao posto = postoGracuacaoService.find(militar.getPostoGraduacao().getId());
					obj.setValorTotalAT(obj.getValorTotalAT() - posto.getCotaParte());
						obj = service.insert(obj);
			// este metodo serve para enviar o precCP para rota
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
					// created gera o codigo 201 (cadastrado com sucesso)
					return ResponseEntity.created(uri).build();
	}
	
	// retornando um numero X de objetos (pages)
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<AuxilioTransporteDTO>> findPage(
		// @RequestParam serve para tornar os parametros opcionais	
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="valorTotalAT")String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC")String direction) {
				Page<AuxilioTransporte> list = service.findPage(page,linesPerPage,orderBy, direction);
					// percorrendo a lista para declarar o DTO correspondente
				Page<AuxilioTransporteDTO> listDTO = list.map(obj -> new AuxilioTransporteDTO(obj));
						return ResponseEntity.ok().body(listDTO);	
	}	
		
}
