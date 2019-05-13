package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.Aditamento;

@Repository
public interface AditamentoDAO extends JpaRepository<Aditamento, Integer> {
	
	
}
