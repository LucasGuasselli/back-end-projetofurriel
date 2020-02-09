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

import com.lucasguasselli.projetofurriel.dao.SaqueAtrasadoDAO;
import com.lucasguasselli.projetofurriel.domain.Aditamento;
import com.lucasguasselli.projetofurriel.domain.DespesaAAnular;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.SaqueAtrasado;
import com.lucasguasselli.projetofurriel.dto.DespesaAAnularNewDTO;
import com.lucasguasselli.projetofurriel.dto.SaqueAtrasadoDTO;
import com.lucasguasselli.projetofurriel.dto.SaqueAtrasadoNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class SaqueAtrasadoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private SaqueAtrasadoDAO SaqueAtrasadoDAO;
	
	public SaqueAtrasado find(Integer id) {
		Optional<SaqueAtrasado> obj = SaqueAtrasadoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + SaqueAtrasado.class.getName()));
	}

  	@Transactional
	public SaqueAtrasado insert(SaqueAtrasado obj) {
		return SaqueAtrasadoDAO.save(obj);
	}
	
	public SaqueAtrasado update(SaqueAtrasado obj) {
		SaqueAtrasado newObj = find(obj.getId());
		updateData(newObj, obj);
			return SaqueAtrasadoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			SaqueAtrasadoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Pagamento Atrasado****");
		}
	}
	
	public List<SaqueAtrasado> findAll(){
		return SaqueAtrasadoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<SaqueAtrasado> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return SaqueAtrasadoDAO.findAll(pageRequest);
	}

	
	// a partir de um DTO vai ser construido e retornado um objeto ExclusaoAAnular
	public SaqueAtrasado fromDTO(SaqueAtrasadoDTO objDTO) {
		return new SaqueAtrasado( objDTO.getMesReferencia(), objDTO.getQuantidadeDias(), objDTO.getMotivo(), objDTO.getValor(), objDTO.getData());
	}
		
	public SaqueAtrasado fromDTO(SaqueAtrasadoNewDTO objNewDTO) {
		SaqueAtrasado pagamentoAtrasado = new SaqueAtrasado(objNewDTO.getMesReferencia(), objNewDTO.getQuantidadeDias(), objNewDTO.getMotivo(),objNewDTO.getValor(), objNewDTO.getData());
		Militar militar = new Militar(objNewDTO.getMilitarPrecCP());
		Aditamento aditamento = new Aditamento(objNewDTO.getAditamentoId());
		pagamentoAtrasado.setMilitar(militar);
		pagamentoAtrasado.setAditamento(aditamento);
			return pagamentoAtrasado;
	}	
			
	private void updateData(SaqueAtrasado newObj, SaqueAtrasado obj) {
		newObj.setId(obj.getId());
		newObj.setData(obj.getData());
		newObj.setValor(obj.getValor());
		newObj.setMotivo(obj.getMotivo());
	}
	
	// transformando um obj PagamentoAtrasado em obj PagamentoAtrasadoNewDTO
	public SaqueAtrasadoNewDTO toNewDTO(SaqueAtrasado obj) {
		SaqueAtrasadoNewDTO pagamentoNewDTO = new SaqueAtrasadoNewDTO(
				obj.getId(), obj.getMesReferencia(), obj.getQuantidadeDias(), obj.getMotivo(),
				obj.getValor(),	obj.getMilitar().getPrecCP(), obj.getAditamento().getId());
			return pagamentoNewDTO;
	}
		
	// transformando uma lista de obj PagamentoAtrasado em uma lista obj PagamentoAtrasadoNewDTO
	public List<SaqueAtrasadoNewDTO> listToNewDTO(List<SaqueAtrasado> lista) {
		List<SaqueAtrasadoNewDTO> pagamentosNewDTO = new ArrayList<SaqueAtrasadoNewDTO>();
		
		for(int i = 0; i < lista.size(); i++) {
			pagamentosNewDTO.add(new SaqueAtrasadoNewDTO(lista.get(i).getId(),
					lista.get(i).getMesReferencia(),lista.get(i).getQuantidadeDias(),
					lista.get(i).getMotivo(), lista.get(i).getValor(),
					lista.get(i).getMilitar().getPrecCP(),
					lista.get(i).getAditamento().getId()));
		}				
				return pagamentosNewDTO;
		}
}
