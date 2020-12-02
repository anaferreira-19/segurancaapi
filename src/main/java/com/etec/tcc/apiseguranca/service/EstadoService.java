package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Estado;
import com.etec.tcc.apiseguranca.repository.EstadoRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> listarEstados(){
		return estadoRepository.findAll();
	}
	
	public Page<Estado> pesquisar(String nome, Pageable pageable){
		return estadoRepository.findByNomeContaining(nome, pageable);
	}
	
	public Estado find(Integer id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		return estado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + Estado.class.getName())); 
	}
	
	public Estado insert(Estado estado) {
		return estadoRepository.save(estado);
	}

	public Estado update(int id, Estado estado) {
		Estado estadoSalva = find(id);
		
		BeanUtils.copyProperties(estado, estadoSalva, "id");
		
		return estadoRepository.save(estadoSalva);
	}
	
	public void delete(Integer id) {
		find(id);
		estadoRepository.deleteById(id);
	}
}
