package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Categoria;
import com.etec.tcc.apiseguranca.repository.CategoriaRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarCategorias(){
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> pesquisar(String nome, Pageable pageable){
		return categoriaRepository.findByNomeContaining(nome, pageable);
	}
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + Categoria.class.getName())); 
	}
	
	public Categoria insert(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(int id, Categoria categoria) {
		Categoria categoriaSalva = find(id);
		
		BeanUtils.copyProperties(categoria, categoriaSalva, "id");
		
		return categoriaRepository.save(categoriaSalva);
	}
	
	public void delete(Integer id) {
		find(id);
		categoriaRepository.deleteById(id);
	}
}
