package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.ExclusaoAuxilioTransporte;

@Repository
public interface ExclusaoDAO extends JpaRepository<ExclusaoAuxilioTransporte, Integer> {
	
	
}
