package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer>{
	public Page<Pais> findByNomeContaining(String nome, Pageable pageable);
}
