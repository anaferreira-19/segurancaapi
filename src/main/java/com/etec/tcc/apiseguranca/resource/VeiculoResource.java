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

import com.etec.tcc.apiseguranca.model.Veiculo;
import com.etec.tcc.apiseguranca.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping("/todos")
	public List<Veiculo> listarTodosVeiculos(){
		return veiculoService.listarVeiculos();
	}
	
	@GetMapping()
	public Page<Veiculo> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return veiculoService.pesquisar(nome, pageable);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> porId(@PathVariable Integer id){
		Veiculo veiculo = veiculoService.find(id);
		return ResponseEntity.ok().body(veiculo);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Veiculo veiculo){
		veiculo = veiculoService.insert(veiculo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(veiculo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		veiculoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Veiculo> update(@PathVariable Integer id, 
			@RequestBody Veiculo veiculo){
		try {
			Veiculo veiculoSalvo = veiculoService.update(id, veiculo);
			return ResponseEntity.ok(veiculoSalvo);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
}
