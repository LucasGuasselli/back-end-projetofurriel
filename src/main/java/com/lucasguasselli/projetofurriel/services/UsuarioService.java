package com.lucasguasselli.projetofurriel.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucasguasselli.projetofurriel.dao.UsuarioDAO;
import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;
import com.lucasguasselli.projetofurriel.domain.Usuario;
import com.lucasguasselli.projetofurriel.dto.UsuarioDTO;
import com.lucasguasselli.projetofurriel.dto.UsuarioNewDTO;
import com.lucasguasselli.projetofurriel.services.exceptions.DataIntegrityException;
import com.lucasguasselli.projetofurriel.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired  // significa que vai ser automaticamente instanciada pelo Spring
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = usuarioDAO.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto nao encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

  	@Transactional
	public Usuario insert(Usuario obj) {
		return usuarioDAO.save(obj);
	}
	
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
			return usuarioDAO.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			System.out.println(id);
			usuarioDAO.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Usuario****");
		}
	}
	
	public List<Usuario> findAll(){
		return usuarioDAO.findAll();
	}
	
	// a partir de um DTO vai ser construido e retornado um objeto ExclusaoAAnular
	public Usuario fromDTO(UsuarioDTO objDTO) {
		return new Usuario(objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf(), null);
	}
	
	public Usuario fromDTO(UsuarioNewDTO objDTO) {
		Usuario usuario = new Usuario(objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf(), pe.encode(objDTO.getSenha()));
		PostoGraduacao postoGraduacao = new PostoGraduacao(objDTO.getPostoGraduacaoId());
		usuario.setPostoGraduacao(postoGraduacao);
			return usuario;
	}

	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setSenha(obj.getSenha());
    }
	
}
