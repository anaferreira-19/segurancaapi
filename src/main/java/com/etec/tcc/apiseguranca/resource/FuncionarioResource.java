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

import com.etec.tcc.apiseguranca.model.Funcionario;
import com.etec.tcc.apiseguranca.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping("/todos")
	public List<Funcionario> listarTodosFuncionarios(){
		return funcionarioService.listarFuncionarios();
	}
	
	@GetMapping()
	public Page<Funcionario> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return funcionarioService.pesquisar(nome, pageable);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> porId(@PathVariable Integer id){
		Funcionario funcionario = funcionarioService.find(id);
		return ResponseEntity.ok().body(funcionario);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Funcionario funcionario){
		funcionario = funcionarioService.insert(funcionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(funcionario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		funcionarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Funcionario> update(@PathVariable Integer id, 
			@RequestBody Funcionario funcionario){
		try {
			Funcionario funcionarioSalvo = funcionarioService.update(id, funcionario);
			return ResponseEntity.ok(funcionarioSalvo);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
}
