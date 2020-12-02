package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	public Page<Endereco> findByRuaContaining(String rua, Pageable pageable);
}
