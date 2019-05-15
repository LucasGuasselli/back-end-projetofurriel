package com.lucasguasselli.projetofurriel.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.AlteracaoValorPassagemDAO;
import com.lucasguasselli.projetofurriel.domain.Aditamento;
import com.lucasguasselli.projetofurriel.domain.Militar;
import com.lucasguasselli.projetofurriel.domain.AlteracaoValorPassagem;
import com.lucasguasselli.projetofurriel.dto.AlteracaoValorPassagemDTO;
import com.lucasguasselli.projetofurriel.dto.AlteracaoValorPassagemNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class AlteracaoValorPassagemService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private AlteracaoValorPassagemDAO alteracaoValorPassagemDAO;
	
	public AlteracaoValorPassagem find(Integer id) {
		Optional<AlteracaoValorPassagem> obj = alteracaoValorPassagemDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + AlteracaoValorPassagem.class.getName()));
	}

  	@Transactional
	public AlteracaoValorPassagem insert(AlteracaoValorPassagem obj) {
		return alteracaoValorPassagemDAO.save(obj);
	}
	
	public AlteracaoValorPassagem update(AlteracaoValorPassagem obj) {
		AlteracaoValorPassagem newObj = find(obj.getId());
		updateData(newObj, obj);
			return alteracaoValorPassagemDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			alteracaoValorPassagemDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Pagamento Atrasado****");
		}
	}
	
	public List<AlteracaoValorPassagem> findAll(){
		return alteracaoValorPassagemDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<AlteracaoValorPassagem> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return alteracaoValorPassagemDAO.findAll(pageRequest);
	}

	// a partir de um DTO vai ser construido e retornado um objeto ExclusaoAAnular
	public AlteracaoValorPassagem fromDTO(AlteracaoValorPassagemDTO objDTO) {
		return new AlteracaoValorPassagem(objDTO.getDataInicio(), objDTO.getMotivo(), objDTO.getValor());
	}		
	
	public AlteracaoValorPassagem fromDTO(AlteracaoValorPassagemNewDTO objNewDTO) {
		AlteracaoValorPassagem alteracaoValorPassagem = new AlteracaoValorPassagem(objNewDTO.getDataInicio(), objNewDTO.getMotivo(), objNewDTO.getValor());
		Militar militar = new Militar(objNewDTO.getMilitarPrecCP());
		Aditamento aditamento = new Aditamento(objNewDTO.getAditamentoId());
		alteracaoValorPassagem.setMilitar(militar);
		alteracaoValorPassagem.setAditamento(aditamento);
			return alteracaoValorPassagem;
	}	
			
	private void updateData(AlteracaoValorPassagem newObj, AlteracaoValorPassagem obj) {
		newObj.setId(obj.getId());
		newObj.setDataInicio(obj.getDataInicio());
		newObj.setValor(obj.getValor());
		newObj.setMotivo(obj.getMotivo());
	}
	
}
