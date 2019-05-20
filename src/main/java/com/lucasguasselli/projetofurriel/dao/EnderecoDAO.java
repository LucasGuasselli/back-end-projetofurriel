package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.Endereco;

@Repository
public interface EnderecoDAO extends JpaRepository<Endereco, Integer> {
	
	@Query("SELECT DISTINCT obj FROM Endereco obj WHERE obj.militar.id = :precCP")
	Endereco search(@Param("precCP") Integer precCP);

}
