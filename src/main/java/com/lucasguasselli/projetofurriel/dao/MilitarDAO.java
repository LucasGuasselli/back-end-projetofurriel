package com.lucasguasselli.projetofurriel.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.Militar;

@Repository
public interface MilitarDAO extends JpaRepository<Militar, Integer> {
	
	@Query("SELECT DISTINCT obj FROM Militar obj WHERE obj.nome LIKE %:nome%")
	Page<Militar> search(@Param("nome") String nome, Pageable PageRequest);
}
