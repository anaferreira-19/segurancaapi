package com.etec.tcc.apiseguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.tcc.apiseguranca.model.Alerta;
import com.etec.tcc.apiseguranca.repository.alerta.AlertaRepositoryQuery;

public interface AlertaRepository extends JpaRepository<Alerta, Integer>, AlertaRepositoryQuery {

}
