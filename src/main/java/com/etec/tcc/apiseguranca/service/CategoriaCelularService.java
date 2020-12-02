package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.CategoriaCelular;
import com.etec.tcc.apiseguranca.repository.CategoriaCelularRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaCelularService {
	
	@Autowired
	private CategoriaCelularRepository categoriaCelularRepository;
	
	public List<CategoriaCelular> listarCategoriasCelular(){
		return categoriaCelularRepository.findAll();
	}
	
	public Page<CategoriaCelular> pesquisar(String nome, Pageable pageable){
		return categoriaCelularRepository.findByNomeContaining(nome, pageable);
	}
	
	public CategoriaCelular find(Integer id) {
		Optional<CategoriaCelular> categoriaCelular = categoriaCelularRepository.findById(id);
		return categoriaCelular.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + CategoriaCelular.class.getName())); 
	}
	
	public CategoriaCelular insert(CategoriaCelular categoriaCelular) {
		return categoriaCelularRepository.save(categoriaCelular);
	}
	
	public CategoriaCelular update(int id, CategoriaCelular categoriaCelular) {
		CategoriaCelular categoriaSalva = find(id);
		
		BeanUtils.copyProperties(categoriaCelular, categoriaSalva, "id");
		
		return categoriaCelularRepository.save(categoriaSalva);
	}
	
	public void delete(Integer id) {
		find(id);
		categoriaCelularRepository.deleteById(id);
	}
}

