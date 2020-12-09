package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.ClienteAdministrador;

@Repository
public interface ClienteAdministradorRepository extends JpaRepository<ClienteAdministrador, Integer>{
	public Page<ClienteAdministrador> findByNomeContaining(String nome, Pageable pageable);
}
