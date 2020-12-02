package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.CategoriaCelular;

@Repository
public interface CategoriaCelularRepository extends JpaRepository<CategoriaCelular, Integer> {
	public Page<CategoriaCelular> findByNomeContaining(String nome, Pageable pageable);
}
