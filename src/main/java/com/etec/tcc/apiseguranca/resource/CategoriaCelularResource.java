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

import com.etec.tcc.apiseguranca.model.CategoriaCelular;
import com.etec.tcc.apiseguranca.service.CategoriaCelularService;

@RestController
@RequestMapping("/categoriascelular")
public class CategoriaCelularResource {
	
	@Autowired
	private CategoriaCelularService categoriaCelularService;
	
	@GetMapping("/todas")
	public List<CategoriaCelular> listarTodasCategoriasCelular(){
		return categoriaCelularService.listarCategoriasCelular();
	}
	@GetMapping()
	public Page<CategoriaCelular> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return categoriaCelularService.pesquisar(nome, pageable);	
	}
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaCelular> porId(@PathVariable Integer id){
		CategoriaCelular categoriaCelular = categoriaCelularService.find(id);
		return ResponseEntity.ok().body(categoriaCelular);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody CategoriaCelular categoriaCelular){
		categoriaCelular = categoriaCelularService.insert(categoriaCelular);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(categoriaCelular.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		categoriaCelularService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CategoriaCelular> update(@PathVariable Integer id, 
			@RequestBody CategoriaCelular categoriaCelular){
		try {
			CategoriaCelular categoriaCelularSalva = categoriaCelularService.update(id, categoriaCelular);
			return ResponseEntity.ok(categoriaCelularSalva);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
	
}
