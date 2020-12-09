package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etec.tcc.apiseguranca.model.Alerta;
import com.etec.tcc.apiseguranca.repository.alerta.AlertaRepositoryQuery;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Integer>, AlertaRepositoryQuery {

}
