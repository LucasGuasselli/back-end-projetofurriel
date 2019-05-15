package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.AlteracaoValorPassagem;

@Repository
public interface AlteracaoValorPassagemDAO extends JpaRepository<AlteracaoValorPassagem, Integer> {
	
	
}
