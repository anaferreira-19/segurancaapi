package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Funcionario;
import com.etec.tcc.apiseguranca.repository.FuncionarioRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public List<Funcionario> listarFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	public Page<Funcionario> pesquisar(String nome, Pageable pageable){
		return funcionarioRepository.findByNomeContaining(nome, pageable);
	}
	
	public Funcionario find(Integer id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return funcionario.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + Funcionario.class.getName())); 
	}
	
	public Funcionario insert(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	public Funcionario update(int id, Funcionario funcionario) {
		Funcionario funcionarioSalvo = find(id);
		
		BeanUtils.copyProperties(funcionario, funcionarioSalvo, "id");
		
		return funcionarioRepository.save(funcionarioSalvo);
	}
	
	public void delete(Integer id) {
		find(id);
		funcionarioRepository.deleteById(id);
	}
}
