package com.lucasguasselli.projetofurriel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasguasselli.projetofurriel.domain.Passagem;

@Repository
public interface PassagemDAO extends JpaRepository<Passagem, Integer> {

}
