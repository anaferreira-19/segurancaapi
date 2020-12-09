package com.etec.tcc.apiseguranca.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.etec.tcc.apiseguranca.model.ClienteAdministrador;
import com.etec.tcc.apiseguranca.service.ClienteAdministradorService;

@RestController
@RequestMapping("/clientesadministrador")
public class ClienteAdministradorResource {
	
	@Autowired
	private ClienteAdministradorService clienteAdministradorService;
	
	@GetMapping("/todos")
	public List<ClienteAdministrador> listarTodosClientesAdministrador(){
		return clienteAdministradorService.listarClienteAdministrador();
	}
	
	@GetMapping()
	public Page<ClienteAdministrador> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return clienteAdministradorService.pesquisar(nome, pageable);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteAdministrador> porId(@PathVariable Integer id){
		ClienteAdministrador cliente = clienteAdministradorService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ClienteAdministrador clienteAdministrador){
		clienteAdministrador = clienteAdministradorService.insert(clienteAdministrador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(clienteAdministrador.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		clienteAdministradorService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ClienteAdministrador> update(@PathVariable Integer id, 
			@RequestBody ClienteAdministrador cliente){
		try {
			ClienteAdministrador clienteSalvo = clienteAdministradorService.update(id, cliente);
			return ResponseEntity.ok(clienteSalvo);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
	
}
