package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Cidade;
import com.etec.tcc.apiseguranca.repository.CidadeRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> listarCidades(){
		return cidadeRepository.findAll();
	}
	
	public Page<Cidade> pesquisar(String nome, Pageable pageable){
		return cidadeRepository.findByNomeContaining(nome, pageable);
	}
	
	public Cidade find(Integer id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		return cidade.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + Cidade.class.getName())); 
	}
	
	public Cidade insert(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	public Cidade update(int id, Cidade cidade) {
		Cidade cidadeSalva = find(id);

		BeanUtils.copyProperties(cidade, cidadeSalva, "id");

		return cidadeRepository.save(cidadeSalva);
	}
	
	public void delete(Integer id) {
		find(id);
		cidadeRepository.deleteById(id);
	}
}
