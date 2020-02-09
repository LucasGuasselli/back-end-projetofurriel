package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.SaqueAtrasado;

@Repository
public interface SaqueAtrasadoDAO extends JpaRepository<SaqueAtrasado, Integer> {
	
	
}
