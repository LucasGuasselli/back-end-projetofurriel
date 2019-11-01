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
import com.lucasguasselli.projetofurriel.domain.Passagem;
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
	@Autowired
	private PassagemService passagemService;
	
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
			aux = updateValues(aux, valorTotal, posto.getCotaParte());
		// considerando o auxilio como atualizado
			aux.setAtualizacao(true);
				auxilioTransporteDAO.save(aux);		
	}	
	
	// alterar os auxilios transportes que estao desatualizados
	public void update(List<AuxilioTransporte> auxilios, List<Passagem> passagens) {
		List<Conducao> conducoes = conducaoService.findAll();
		List<ConducaoNewDTO> allConducoes = conducaoService.listToNewDTO(conducoes);
		List<ConducaoNewDTO> conducoesAuxilioTransporte = new ArrayList<ConducaoNewDTO>();
		// DIVIDIR EM DOIS METODOS, UM PARA ATUALIZAR OS QUE FORAM ATUALIZADOS E OUTRO PARA ATUALIZAR OS QUE ESTAO ERRADOS
		
		// percorrendo todos auxilios
			for(int i = 0; i < auxilios.size(); i++) {
				// se o auxilio ja estiver desatualizado pula a comparacao
				if(auxilios.get(i).isAtualizacao() == true) {
					// buscando as conducoes do auxilio correspondente
					for(int k = 0; k < allConducoes.size(); k++) {
						// armazenando as conducoes correspondentes ao auxilio
						if (auxilios.get(i).getId() == allConducoes.get(k).getAuxilioTransporteId()) {
							conducoesAuxilioTransporte.add(allConducoes.get(k));
						}
					}
					// comparando as conducoes do auxilio transporte com as passagens
					for(int c = 0; c < conducoesAuxilioTransporte.size(); c++) {
						// caso uma conducao esteja desatualizada (false), o metodo nao perde tempo com as outras conducoes
						if(auxilios.get(i).isAtualizacao() == true) {
							for (int p = 0; p < passagens.size(); p++) {
								// se o tipo de transporte for igual compara o valor
								if (conducoesAuxilioTransporte.get(c).getTipoDeTransporte().equals( passagens.get(p).getTipoTransporte())) {
									// se o valor da conducao for igual ao das passagens, nao faz nada, ELSE seta false para atualizacao
									if (conducoesAuxilioTransporte.get(c).getValor() == passagens.get(p).getValor()) {
										System.out.println("passagem atualizada");
									}else {
										auxilios.get(i).setAtualizacao(false);
											// salvando no banco
												auxilioTransporteDAO.save(auxilios.get(i));
									}
								}
							}
						}
					}
				}				
			} // fecha for externo
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
	
		public MilitarNewDTO toNewDTO(Militar obj) {
			MilitarNewDTO militarNewDTO = new MilitarNewDTO(obj.getPrecCP(), obj.getNome(), obj.getPostoGraduacao().getId());
				return militarNewDTO;
		}
	
	public AuxilioTransporte fromDTO(AuxilioTransporteNewDTO objDTO) {
		Militar militar = new Militar(objDTO.getMilitarPrecCP());
		AuxilioTransporte auxilioTransporte = new AuxilioTransporte(objDTO.getValorTotalAT(), objDTO.getValorDiarioAT(),
				objDTO.isExclusao(), objDTO.isAtualizacao(), objDTO.isEntregaSPP(), militar);
			return auxilioTransporte;
	}	
	
	// a partir de um DTO vai ser construido e retornado um objeto AuxilioTransporte
	public AuxilioTransporte fromDTO(AuxilioTransporteDTO objDTO) {
		return new AuxilioTransporte(objDTO.getValorTotalAT(),objDTO.getValorDiarioAT(),
				objDTO.isExclusao(), objDTO.isAtualizacao(), objDTO.isEntregaSPP());
	}
		
	private void updateData(AuxilioTransporte newObj, AuxilioTransporte obj) {
		newObj.setId(obj.getId());
		newObj.setValorTotalAT(obj.getValorTotalAT());
		newObj.setValorDiarioAT(obj.getValorDiarioAT());
		newObj.setExclusao(obj.isExclusao());
		newObj.setAtualizacao(obj.isAtualizacao());
		newObj.setEntregaSPP(obj.isEntregaSPP());
	}
	
	// atualiza os valores quando uma conducao e inserida
	private AuxilioTransporte updateValues(AuxilioTransporte aux, double valor, double cotaParte) {
		double valorTotalAT =  ((valor * 22) - cotaParte);
		double valorDiarioAT = valorTotalAT / 22;
			aux.setValorTotalAT(valorTotalAT);
			aux.setValorDiarioAT(valorDiarioAT);
				return aux;
	}	
	
	// transformando um obj AuxilioTransporte em obj AuxilioTransporteNewDTO
		public AuxilioTransporteNewDTO toNewDTO(AuxilioTransporte obj) {
			AuxilioTransporteNewDTO auxilioTransporteNewDTO = new AuxilioTransporteNewDTO(obj.getId(),
				obj.getValorTotalAT(), obj.getValorDiarioAT(), obj.isExclusao(), obj.isAtualizacao(),
				obj.isEntregaSPP(), obj.getMilitar().getPrecCP());
			
				return auxilioTransporteNewDTO;
		}
	
	// transformando um obj AuxilioTransporte em obj AuxilioTransporteNewDTO
	public List<AuxilioTransporteNewDTO> listToNewDTO(List<AuxilioTransporte> lista) {
		List<AuxilioTransporteNewDTO> auxiliosNewDTO = new ArrayList<AuxilioTransporteNewDTO>();
		
		for(int i = 0; i < lista.size(); i++) {
			auxiliosNewDTO.add(new AuxilioTransporteNewDTO(lista.get(i).getId(), lista.get(i).getValorTotalAT(),
				lista.get(i).getValorDiarioAT(), lista.get(i).isExclusao(), lista.get(i).isAtualizacao(),
				lista.get(i).isEntregaSPP(), lista.get(i).getMilitar().getPrecCP()));				 	
		}				
			return auxiliosNewDTO;
	}
}
