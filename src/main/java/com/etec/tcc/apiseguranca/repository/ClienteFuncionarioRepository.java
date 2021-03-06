package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.ClienteFuncionario;

@Repository
public interface ClienteFuncionarioRepository extends JpaRepository<ClienteFuncionario, Integer>{
	public Page<ClienteFuncionario> findByNomeContaining(String nome, Pageable pageable);

}
