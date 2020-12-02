package com.etec.tcc.apiseguranca.repository.alerta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.etec.tcc.apiseguranca.model.Alerta;
import com.etec.tcc.apiseguranca.repository.filter.AlertaFilter;

public interface AlertaRepositoryQuery{
	public Page<Alerta> filtrar(AlertaFilter alertaFilter, Pageable pageable);	
}
