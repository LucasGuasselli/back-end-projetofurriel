package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{

}
