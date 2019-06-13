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

import com.lucasguasselli.projetofurriel.dao.PagamentoAtrasadoDAO;
import com.lucasguasselli.projetofurriel.domain.Aditamento;
import com.lucasguasselli.projetofurriel.domain.DespesaAAnular;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.PagamentoAtrasado;
import com.lucasguasselli.projetofurriel.dto.DespesaAAnularNewDTO;
import com.lucasguasselli.projetofurriel.dto.PagamentoAtrasadoDTO;
import com.lucasguasselli.projetofurriel.dto.PagamentoAtrasadoNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class PagamentoAtrasadoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PagamentoAtrasadoDAO pagamentoAtrasadoDAO;
	
	public PagamentoAtrasado find(Integer id) {
		Optional<PagamentoAtrasado> obj = pagamentoAtrasadoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + PagamentoAtrasado.class.getName()));
	}

  	@Transactional
	public PagamentoAtrasado insert(PagamentoAtrasado obj) {
		return pagamentoAtrasadoDAO.save(obj);
	}
	
	public PagamentoAtrasado update(PagamentoAtrasado obj) {
		PagamentoAtrasado newObj = find(obj.getId());
		updateData(newObj, obj);
			return pagamentoAtrasadoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			pagamentoAtrasadoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Pagamento Atrasado****");
		}
	}
	
	public List<PagamentoAtrasado> findAll(){
		return pagamentoAtrasadoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<PagamentoAtrasado> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return pagamentoAtrasadoDAO.findAll(pageRequest);
	}

	
	// a partir de um DTO vai ser construido e retornado um objeto ExclusaoAAnular
	public PagamentoAtrasado fromDTO(PagamentoAtrasadoDTO objDTO) {
		return new PagamentoAtrasado( objDTO.getMesReferencia(), objDTO.getQuantidadeDias(), objDTO.getMotivo(), objDTO.getValor(), objDTO.getData());
	}
		
	public PagamentoAtrasado fromDTO(PagamentoAtrasadoNewDTO objNewDTO) {
		PagamentoAtrasado pagamentoAtrasado = new PagamentoAtrasado(objNewDTO.getMesReferencia(), objNewDTO.getQuantidadeDias(), objNewDTO.getMotivo(),objNewDTO.getValor(), objNewDTO.getData());
		Militar militar = new Militar(objNewDTO.getMilitarPrecCP());
		Aditamento aditamento = new Aditamento(objNewDTO.getAditamentoId());
		pagamentoAtrasado.setMilitar(militar);
		pagamentoAtrasado.setAditamento(aditamento);
			return pagamentoAtrasado;
	}	
			
	private void updateData(PagamentoAtrasado newObj, PagamentoAtrasado obj) {
		newObj.setId(obj.getId());
		newObj.setData(obj.getData());
		newObj.setValor(obj.getValor());
		newObj.setMotivo(obj.getMotivo());
	}
	
	// transformando um obj PagamentoAtrasado em obj PagamentoAtrasadoNewDTO
	public PagamentoAtrasadoNewDTO toNewDTO(PagamentoAtrasado obj) {
		PagamentoAtrasadoNewDTO pagamentoNewDTO = new PagamentoAtrasadoNewDTO(
				obj.getId(), obj.getMesReferencia(), obj.getQuantidadeDias(), obj.getMotivo(),
				obj.getValor(),	obj.getMilitar().getPrecCP(), obj.getAditamento().getId());
			return pagamentoNewDTO;
	}
		
	// transformando uma lista de obj PagamentoAtrasado em uma lista obj PagamentoAtrasadoNewDTO
	public List<PagamentoAtrasadoNewDTO> listToNewDTO(List<PagamentoAtrasado> lista) {
		List<PagamentoAtrasadoNewDTO> pagamentosNewDTO = new ArrayList<PagamentoAtrasadoNewDTO>();
		
		for(int i = 0; i < lista.size(); i++) {
			pagamentosNewDTO.add(new PagamentoAtrasadoNewDTO(lista.get(i).getId(),
					lista.get(i).getMesReferencia(),lista.get(i).getQuantidadeDias(),
					lista.get(i).getMotivo(), lista.get(i).getValor(),
					lista.get(i).getMilitar().getPrecCP(),
					lista.get(i).getAditamento().getId()));
		}				
				return pagamentosNewDTO;
		}
}
