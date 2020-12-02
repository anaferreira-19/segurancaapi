package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Endereco;
import com.etec.tcc.apiseguranca.repository.EnderecoRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Endereco> listarEnderecos(){
		return enderecoRepository.findAll();
	}
	
	public Page<Endereco> pesquisar(String rua, Pageable pageable){
		return enderecoRepository.findByRuaContaining(rua, pageable);
	}
	
	public Endereco find(Integer id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		return endereco.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + Endereco.class.getName())); 
	}
	
	public Endereco insert(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco update(int id, Endereco endereco) {
		Endereco enderecoSalva = find(id);
		
		BeanUtils.copyProperties(endereco, enderecoSalva, "id");
		
		return enderecoRepository.save(enderecoSalva);
	}
	
	public void delete(Integer id) {
		find(id);
		enderecoRepository.deleteById(id);
	}
}
