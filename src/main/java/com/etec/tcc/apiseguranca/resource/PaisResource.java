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

import com.etec.tcc.apiseguranca.model.Pais;
import com.etec.tcc.apiseguranca.service.PaisService;

@RestController
@RequestMapping("/paises")
public class PaisResource {
	
	@Autowired
	private PaisService paisService;
	
	@GetMapping("/todos")
	public List<Pais> listarTodosPaises(){
		return paisService.listarPaises();
	}
	
	@GetMapping()
	public Page<Pais> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return paisService.pesquisar(nome, pageable);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pais> porId(@PathVariable Integer id){
		Pais pais = paisService.find(id);
		return ResponseEntity.ok().body(pais);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Pais pais){
		pais = paisService.insert(pais);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(pais.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		paisService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pais> update(@PathVariable Integer id, 
			@RequestBody Pais pais){
		try {
			Pais paisSalvo = paisService.update(id, pais);
			return ResponseEntity.ok(paisSalvo);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
}
