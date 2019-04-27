package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.Conducao;

@Repository
public interface ConducaoDAO extends JpaRepository<Conducao, Integer> {
	
	
}
