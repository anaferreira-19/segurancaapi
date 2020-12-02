package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	public Page<Categoria> findByNomeContaining(String nome, Pageable pageable);
}
