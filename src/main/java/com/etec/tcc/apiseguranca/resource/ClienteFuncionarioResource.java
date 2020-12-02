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

import com.etec.tcc.apiseguranca.model.ClienteFuncionario;
import com.etec.tcc.apiseguranca.service.ClienteFuncionarioService;

@RestController
@RequestMapping("/clientesfuncionario")
public class ClienteFuncionarioResource {
	@Autowired
	private ClienteFuncionarioService clienteFuncionarioService;
	
	@GetMapping("/todos")
	public List<ClienteFuncionario> listarTodosClientesFuncionario(){
		return clienteFuncionarioService.listarClienteFuncionario();
	}
	
	@GetMapping()
	public Page<ClienteFuncionario> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return clienteFuncionarioService.pesquisar(nome, pageable);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteFuncionario> porId(@PathVariable Integer id){
		ClienteFuncionario cliente = clienteFuncionarioService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ClienteFuncionario cliente){
		cliente = clienteFuncionarioService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		clienteFuncionarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ClienteFuncionario> update(@PathVariable Integer id, 
			@RequestBody ClienteFuncionario cliente){
		try {
			ClienteFuncionario clienteSalvo = clienteFuncionarioService.update(id, cliente);
			return ResponseEntity.ok(clienteSalvo);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
	
}


