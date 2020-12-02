package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.ClienteFuncionario;
import com.etec.tcc.apiseguranca.repository.ClienteFuncionarioRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteFuncionarioService {

	@Autowired
	private ClienteFuncionarioRepository clienteFuncionarioRepository;
	
	public List<ClienteFuncionario> listarClienteFuncionario(){
		return clienteFuncionarioRepository.findAll();
	}
	
	public Page<ClienteFuncionario> pesquisar(String nome, Pageable pageable){
		return clienteFuncionarioRepository.findByNomeContaining(nome, pageable);
	}
	
	public ClienteFuncionario find(Integer id) {
		Optional<ClienteFuncionario> clienteFuncionario = clienteFuncionarioRepository.findById(id);
		return clienteFuncionario.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + ClienteFuncionario.class.getName())); 
	}
	
	public ClienteFuncionario insert(ClienteFuncionario cliente) {
		return clienteFuncionarioRepository.save(cliente);
	}
	
	public ClienteFuncionario update(int id, ClienteFuncionario cliente) {
		ClienteFuncionario clienteSalvo = find(id);
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		
		return clienteFuncionarioRepository.save(clienteSalvo);
	}
	
	public void delete(Integer id) {
		find(id);
		clienteFuncionarioRepository.deleteById(id);
	}
}
