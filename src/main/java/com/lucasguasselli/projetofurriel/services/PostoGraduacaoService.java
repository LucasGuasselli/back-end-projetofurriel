package com.lucasguasselli.projetofurriel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.PostoGraduacaoDAO;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.dto.PostoGraduacaoDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class PostoGraduacaoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private PostoGraduacaoDAO postoGraduacaoDAO;
	
	public PostoGraduacao find(Integer id) {
		Optional<PostoGraduacao> obj = postoGraduacaoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + PostoGraduacao.class.getName()));
	}
	
	public PostoGraduacao insert(PostoGraduacao obj) {
		return postoGraduacaoDAO.save(obj);
	}
	
	public PostoGraduacao update(PostoGraduacao obj) {
		PostoGraduacao newObj = find(obj.getId());
		updateData(newObj, obj);
			return postoGraduacaoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			postoGraduacaoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um postoGraduacao que possui militares");
		}
	}
	
	public List<PostoGraduacao> findAll(){
		return postoGraduacaoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<PostoGraduacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return postoGraduacaoDAO.findAll(pageRequest);
	}
	
	// a partir de um DTO vai ser construido e retornado um objeto postoGraduacao
	public PostoGraduacao fromDTO(PostoGraduacaoDTO objDTO) {
		return new PostoGraduacao(objDTO.getId(),objDTO.getNome(), objDTO.getSoldo(), objDTO.getCotaParte());
	}
	
	private void updateData(PostoGraduacao newObj, PostoGraduacao obj) {
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setSoldo(obj.getSoldo());
		newObj.setCotaParte(obj.getCotaParte());
	}
}
