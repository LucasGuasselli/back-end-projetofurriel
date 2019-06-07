package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.AuxilioTransporte;

@Repository
public interface AuxilioTransporteDAO extends JpaRepository<AuxilioTransporte, Integer> {
	
	
}
