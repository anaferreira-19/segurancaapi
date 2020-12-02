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

import com.etec.tcc.apiseguranca.model.Endereco;
import com.etec.tcc.apiseguranca.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("/todos")
	public List<Endereco> listarTodosEnderecos(){
		return enderecoService.listarEnderecos();
	}
	
	@GetMapping()
	public Page<Endereco> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return enderecoService.pesquisar(nome, pageable);	
	}
	
	@GetMapping("/coordenadas/{id}")
	public ResponseEntity<Endereco> porCoordenada(@PathVariable Integer id, String latitude, String longitude){
		Endereco endereco = enderecoService.find(id);
		return ResponseEntity.ok().body(endereco);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Endereco endereco){
		endereco = enderecoService.insert(endereco);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Endereco> update(@PathVariable Integer id, 
			@RequestBody Endereco endereco){
		try {
			Endereco enderecoSalvo = enderecoService.update(id, endereco);
			return ResponseEntity.ok(enderecoSalvo);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
}
