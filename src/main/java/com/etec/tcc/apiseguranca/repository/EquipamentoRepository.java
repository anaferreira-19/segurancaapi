package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.tcc.apiseguranca.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer>{
	public Page<Equipamento> findByNomeContaining(String nome, Pageable pageable);
}
