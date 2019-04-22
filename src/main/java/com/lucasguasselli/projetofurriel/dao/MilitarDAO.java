package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.Militar;

@Repository
public interface MilitarDAO extends JpaRepository<Militar, Integer> {
	
	
}
