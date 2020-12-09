package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.ClienteAdministrador;
import com.etec.tcc.apiseguranca.repository.ClienteAdministradorRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteAdministradorService {

	@Autowired
	private ClienteAdministradorRepository clienteAdministradorRepository;
	
	public List<ClienteAdministrador> listarClienteAdministrador(){
		return clienteAdministradorRepository.findAll();
	}
	
	public Page<ClienteAdministrador> pesquisar(String nome, Pageable pageable){
		return clienteAdministradorRepository.findByNomeContaining(nome, pageable);
	}
	
	public ClienteAdministrador find(Integer id) {
		Optional<ClienteAdministrador> cliente = clienteAdministradorRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + ClienteAdministrador.class.getName())); 
	}
	
	public ClienteAdministrador insert(ClienteAdministrador clienteAdministrador) {
		return clienteAdministradorRepository.save(clienteAdministrador);
	}
	
	public ClienteAdministrador update(int id, ClienteAdministrador cliente) {
		ClienteAdministrador clienteSalvo = find(id);
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		
		return clienteAdministradorRepository.save(clienteSalvo);
	}
	
	public void delete(Integer id) {
		find(id);
		clienteAdministradorRepository.deleteById(id);
	}
}
