package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	public Page<Funcionario> findByNomeContaining(String nome, Pageable pageable);
}
