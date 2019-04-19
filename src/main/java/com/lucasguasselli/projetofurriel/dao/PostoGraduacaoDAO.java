package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.PostoGraduacao;

@Repository
public interface PostoGraduacaoDAO extends JpaRepository<PostoGraduacao, Integer> {
	
	
}
