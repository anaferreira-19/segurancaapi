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

import com.etec.tcc.apiseguranca.model.Categoria;
import com.etec.tcc.apiseguranca.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/todas")
	public List<Categoria> listarTodasCategorias(){
		return categoriaService.listarCategorias();
	}
	@GetMapping()
	public Page<Categoria> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return categoriaService.pesquisar(nome, pageable);	
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> porId(@PathVariable Integer id){
		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Categoria> update(@PathVariable Integer id, 
			@RequestBody Categoria categoria){
		try {
			Categoria categoriaSalva = categoriaService.update(id, categoria);
			return ResponseEntity.ok(categoriaSalva);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
	
}
