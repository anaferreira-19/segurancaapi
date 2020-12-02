package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Pais;
import com.etec.tcc.apiseguranca.repository.PaisRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class PaisService {

	@Autowired
	private PaisRepository paisRepository;
	
	public List<Pais> listarPaises(){
		return paisRepository.findAll();
	}
	
	public Page<Pais> pesquisar(String nome, Pageable pageable){
		return paisRepository.findByNomeContaining(nome, pageable);
	}
	
	public Pais find(Integer id) {
		Optional<Pais> pais = paisRepository.findById(id);
		return pais.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + Pais.class.getName())); 
	}
	
	public Pais insert(Pais pais) {
		return paisRepository.save(pais);
	}
	
	public Pais update(int id, Pais pais) {
		Pais paisSalvo = find(id);
		
		BeanUtils.copyProperties(pais, paisSalvo, "id");
		
		return paisRepository.save(paisSalvo);
	}
	
	public void delete(Integer id) {
		find(id);
		paisRepository.deleteById(id);
	}
}
