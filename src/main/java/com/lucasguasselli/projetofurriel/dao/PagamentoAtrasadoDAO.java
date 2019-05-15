package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.PagamentoAtrasado;

@Repository
public interface PagamentoAtrasadoDAO extends JpaRepository<PagamentoAtrasado, Integer> {
	
	
}
