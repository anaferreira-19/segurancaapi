package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer>{
	public Page<Equipamento> findByNomeContaining(String nome, Pageable pageable);
}
