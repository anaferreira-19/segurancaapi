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

import com.etec.tcc.apiseguranca.model.Estado;
import com.etec.tcc.apiseguranca.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/todas")
	public List<Estado> listarTodosEstados(){
		return estadoService.listarEstados();
	}
	
	@GetMapping()
	public Page<Estado> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return estadoService.pesquisar(nome, pageable);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> porId(@PathVariable Integer id){
		Estado estado = estadoService.find(id);
		return ResponseEntity.ok().body(estado);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Estado estado){
		estado = estadoService.insert(estado);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		estadoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<Estado> update(@PathVariable Integer id, 
			@RequestBody Estado estado){
		try {
			Estado estadoSalvo = estadoService.update(id, estado);
			return ResponseEntity.ok(estadoSalvo);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
}
