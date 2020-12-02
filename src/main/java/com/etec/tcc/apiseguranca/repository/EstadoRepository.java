package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	public Page<Estado> findByNomeContaining(String nome, Pageable pageable);
}
