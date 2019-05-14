package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.InclusaoAuxilioTransporte;

@Repository
public interface InclusaoDAO extends JpaRepository<InclusaoAuxilioTransporte, Integer> {
	
	
}
