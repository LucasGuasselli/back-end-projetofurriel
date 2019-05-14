package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.DespesaAAnular;

@Repository
public interface DespesaDAO extends JpaRepository<DespesaAAnular, Integer> {
	
	
}
