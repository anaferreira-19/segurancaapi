package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{
	public Page<Veiculo> findByNomeContaining(String nome, Pageable pageable);
}
