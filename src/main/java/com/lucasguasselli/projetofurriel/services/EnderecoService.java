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

import com.lucasguasselli.projetofurriel.dao.EnderecoDAO;
import com.lucasguasselli.projetofurriel.domain.Endereco;
import com.lucasguasselli.projetofurriel.dto.EnderecoDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	
	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private EnderecoDAO enderecoDAO;
	
	public Endereco find(Integer id) {
		Optional<Endereco> obj = enderecoDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}

  	@Transactional
	public Endereco insert(Endereco obj) {
		return enderecoDAO.save(obj);
	}
	
	public Endereco update(Endereco obj) {
		Endereco newObj = find(obj.getId());
		updateData(newObj, obj);
			return enderecoDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			enderecoDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Endereco ****");
		}
	}
	
	public List<Endereco> findAll(){
		return enderecoDAO.findAll();
	}
	
	// retornando uma pagina de dados (limitar resultados)
	public Page<Endereco> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
			return enderecoDAO.findAll(pageRequest);
	}
	
	/*
	// buscando por nome
	public Page<Endereco> search(String nome, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		// List<PostoGraduacao> postosGraduacoes = postoGraduacaoDAO.findAllById(ids);
			return EnderecoDAO.search(nome,pageRequest);
	}
		
	public Endereco fromDTO(EnderecoNewDTO objDTO) {
		Endereco Endereco = new Endereco(objDTO.getPrecCP(),objDTO.getNome());
		PostoGraduacao postoGraduacao = new PostoGraduacao(objDTO.getPostoGraduacaoId());
		Endereco.setPostoGraduacao(postoGraduacao);
			return Endereco;
	}
	
	*/
	
	// a partir de um DTO vai ser construido e retornado um objeto Endereco
	public Endereco fromDTO(EnderecoDTO objDTO) {
		return new Endereco(objDTO.getRua(), objDTO.getBairro(), objDTO.getCidade(), objDTO.getNumero(), objDTO.getComplemento());
	}
		
	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setId(obj.getId());
		newObj.setRua(obj.getRua());
		newObj.setBairro(obj.getBairro());
		newObj.setCidade(obj.getCidade());
		newObj.setNumero(obj.getNumero());
		newObj.setComplemento(obj.getComplemento());
	}
	
}
