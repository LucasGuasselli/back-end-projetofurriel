package com.lucasguasselli.projetofurriel.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.AuxilioTransporteDAO;
import com.lucasguasselli.projetofurriel.domain.AuxilioTransporte;
import com.lucasguasselli.projetofurriel.domain.Conducao;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.dto.AuxilioTransporteDTO;
import com.lucasguasselli.projetofurriel.dto.AuxilioTransporteNewDTO;
import com.lucasguasselli.projetofurriel.dto.ConducaoNewDTO;
import com.lucasguasselli.projetofurriel.dto.MilitarNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class AuxilioTransporteService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private AuxilioTransporteDAO auxilioTransporteDAO;
	@Autowired
	private ConducaoService conducaoService;
	@Autowired
	private MilitarService militarService;
	@Autowired
	private PostoGraduacaoService postoService; 
	
	public AuxilioTransporte find(Integer id) {
		Optional<AuxilioTransporte> obj = auxilioTransporteDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + AuxilioTransporte.class.getName()));
	}

  	@Transactional
	public AuxilioTransporte insert(AuxilioTransporte obj) {
		return auxilioTransporteDAO.save(obj);
	}
	
	public AuxilioTransporte update(AuxilioTransporte obj) {
		AuxilioTransporte newObj = find(obj.getId());
		updateData(newObj, obj);
			return auxilioTransporteDAO.save(newObj);
	}
	
	// atualizando valores do auxilio transporte ao adicionar uma conducao
	public void update(ConducaoNewDTO conducaoNewDTO) {
		// buscando o valor das conducoes
		List<Conducao> list = conducaoService.findAll();
			List<ConducaoNewDTO> listNewDTO = conducaoService.listToNewDTO(list);
				double valorTotal = 0;
			for(int i = 0; i < listNewDTO.size(); i++) {
				if (listNewDTO.get(i).getAuxilioTransporteId() == conducaoNewDTO.getAuxilioTransporteId()) {
					valorTotal += listNewDTO.get(i).getValor();
				}
			}
		AuxilioTransporte aux = this.find(conducaoNewDTO.getAuxilioTransporteId());

		// buscando a cota parte
		Militar militar = militarService.find(aux.getMilitar().getPrecCP());
			MilitarNewDTO militarNewDTO = militarService.toNewDTO(militar);
				PostoGraduacao posto = postoService.find(militarNewDTO.getPostoGraduacaoId());
		// atualizando valores e salvando no Banco de Dados
		aux = updateValueInsertConcucao(aux, valorTotal, posto.getCotaParte());
				auxilioTransporteDAO.save(aux);		
	}
	
	// atualizando valores quando uma conducao e alterada
	public void update(Conducao conducao, ConducaoNewDTO conducaoNewDTO, Integer oldValue) {
		AuxilioTransporte aux = find(conducaoNewDTO.getAuxilioTransporteId());
		updateValueUpdateConcucao(aux, conducao, oldValue);
				auxilioTransporteDAO.save(aux);		
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			auxilioTransporteDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Auxilio Transporte ****");
		}
	}
	
	public List<AuxilioTransporte> findAll(){
		return auxilioTransporteDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<AuxilioTransporte> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return auxilioTransporteDAO.findAll(pageRequest);
	}
	
	// transformando um obj DespesaAAnular em obj DespesaAAnularNewDTO
		public MilitarNewDTO toNewDTO(Militar obj) {
			MilitarNewDTO militarNewDTO = new MilitarNewDTO(obj.getPrecCP(), obj.getNome(), obj.getPostoGraduacao().getId());
				return militarNewDTO;
		}
	
	public AuxilioTransporte fromDTO(AuxilioTransporteNewDTO objDTO) {
		Militar militar = new Militar(objDTO.getMilitarPrecCP());
		AuxilioTransporte auxilioTransporte = new AuxilioTransporte(objDTO.getValorTotalAT(),
				objDTO.getValorDiarioAT(), objDTO.isExclusao(), militar);
			return auxilioTransporte;
	}	
	
	// a partir de um DTO vai ser construido e retornado um objeto AuxilioTransporte
	public AuxilioTransporte fromDTO(AuxilioTransporteDTO objDTO) {
		return new AuxilioTransporte(objDTO.getValorTotalAT(),objDTO.getValorDiarioAT(), objDTO.isExclusao());
	}
		
	private void updateData(AuxilioTransporte newObj, AuxilioTransporte obj) {
		newObj.setId(obj.getId());
		newObj.setValorTotalAT(obj.getValorTotalAT());
		newObj.setValorDiarioAT(obj.getValorDiarioAT());
		newObj.setExclusao(obj.isExclusao());
	}
	
	// atualiza os valores quando uma conducao e inserida
	private AuxilioTransporte updateValueInsertConcucao(AuxilioTransporte aux, double valor, double cotaParte) {
		double valorTotalAT =  ((valor * 22) - cotaParte);
		double valorDiarioAT = valorTotalAT / 22;
			aux.setValorTotalAT(valorTotalAT);
			aux.setValorDiarioAT(valorDiarioAT);
				return aux;
	}
	
	// atualiza os valores quando uma conducao e alterada
	private void updateValueUpdateConcucao(AuxilioTransporte newObj, Conducao obj, Integer oldValue) {
		double valorTotalAT = newObj.getValorTotalAT() - (oldValue * 22);
		valorTotalAT = valorTotalAT + (obj.getValor() * 22);
		double valorDiarioAT = valorTotalAT / 22;
			newObj.setValorTotalAT(valorTotalAT);
			newObj.setValorDiarioAT(valorDiarioAT);
	}
	
	private void updateValueDeleteConducao(AuxilioTransporte newObj, Conducao obj) {
		double valorTotalAT = newObj.getValorTotalAT() - (obj.getValor() * 22);
		double valorDiarioAT = valorTotalAT / 22;
			newObj.setValorTotalAT(valorTotalAT);
			newObj.setValorDiarioAT(valorDiarioAT);
	}
	
	// transformando um obj AuxilioTransporte em obj AuxilioTransporteNewDTO
		public AuxilioTransporteNewDTO toNewDTO(AuxilioTransporte obj) {
			AuxilioTransporteNewDTO auxilioTransporteNewDTO = new AuxilioTransporteNewDTO(obj.getId(),
				obj.getValorTotalAT(), obj.getValorDiarioAT(), obj.isExclusao(), obj.getMilitar().getPrecCP());
			
				return auxilioTransporteNewDTO;
		}
	
	// transformando um obj AuxilioTransporte em obj AuxilioTransporteNewDTO
	public List<AuxilioTransporteNewDTO> listToNewDTO(List<AuxilioTransporte> lista) {
		List<AuxilioTransporteNewDTO> auxiliosNewDTO = new ArrayList<AuxilioTransporteNewDTO>();
		
		for(int i = 0; i < lista.size(); i++) {
			auxiliosNewDTO.add(new AuxilioTransporteNewDTO(lista.get(i).getId(),
				lista.get(i).getValorTotalAT(), lista.get(i).getValorDiarioAT(),
				lista.get(i).isExclusao(), lista.get(i).getMilitar().getPrecCP()));
				 	
		}				
			return auxiliosNewDTO;
	}
}
