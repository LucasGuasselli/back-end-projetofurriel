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

import com.lucasguasselli.projetofurriel.dao.AditamentoDAO;
import com.lucasguasselli.projetofurriel.domain.Aditamento;
import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.dto.AditamentoDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class AditamentoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private AditamentoDAO aditamentoDAO;
	
	public Aditamento find(Integer id) {
		Optional<Aditamento> obj = aditamentoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Aditamento.class.getName()));
	}

  	@Transactional
	public Aditamento insert(Aditamento obj) {
		return aditamentoDAO.save(obj);
	}
	
	public Aditamento update(Aditamento obj) {
		Aditamento newObj = find(obj.getId());
		updateData(newObj, obj);
			return aditamentoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			aditamentoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Aditamento que possui eventos relacionados");
		}
	}
	
	public List<Aditamento> findAll(){
		return aditamentoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<Aditamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return aditamentoDAO.findAll(pageRequest);
	}
	
	public Aditamento fromDTO(AditamentoDTO objDTO) {
		return new Aditamento(objDTO.getNome(), objDTO.getData(), objDTO.getDespesaPeriodo(), objDTO.getExclusaoTexto());
	}	
	
	private void updateData(Aditamento newObj, Aditamento obj) {
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setData(obj.getData());
		newObj.setAssinatura(obj.getAssinatura());
	}
	 
}
