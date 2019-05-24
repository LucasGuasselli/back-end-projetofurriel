package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.DespesaAAnular;

@Repository
public interface DespesaDAO extends JpaRepository<DespesaAAnular, Integer> {
	
	@Query("SELECT DISTINCT obj FROM DespesaAAnular obj WHERE obj.id = :id")
	DespesaAAnular searchPrecCPById(@Param("id") Integer id);
	
}
