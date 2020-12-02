package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Alerta;
import com.etec.tcc.apiseguranca.repository.AlertaRepository;
import com.etec.tcc.apiseguranca.repository.filter.AlertaFilter;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;


@Service
public class AlertaService {
	
	@Autowired
	private AlertaRepository alertaRepository;
	
	public List<Alerta>listarAlerta(){
		return alertaRepository.findAll();
	}
	
	public Page<Alerta> pesquisar(AlertaFilter alertaFilter, Pageable pageable){
		return alertaRepository.filtrar(alertaFilter, pageable);
	}
	
	public Alerta insert(Alerta alerta) {
		return alertaRepository.save(alerta);
	}
	
	public Alerta update(int id, Alerta alerta) {
		Alerta alertaSalvo = find(id);
		
		BeanUtils.copyProperties(alerta, alertaSalvo, "id");
		return alertaRepository.save(alertaSalvo);
	}
	
	public void delete(Integer id) {
		find(id);
		alertaRepository.deleteById(id);
	}
	
	public Alerta find(int id) {
		Optional<Alerta> pedido = alertaRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id: " + id + " Tipo: " + Alerta.class.getName()));
	}
}
