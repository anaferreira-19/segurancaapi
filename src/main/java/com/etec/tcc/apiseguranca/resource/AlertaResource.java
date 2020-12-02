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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.etec.tcc.apiseguranca.model.Alerta;
import com.etec.tcc.apiseguranca.repository.filter.AlertaFilter;
import com.etec.tcc.apiseguranca.service.AlertaService;

@RestController
@RequestMapping("/alertas")
public class AlertaResource {

	@Autowired
	private AlertaService alertaService;
	
	@GetMapping("/todos")
	public List<Alerta>listarTodosAlertas(){
		return alertaService.listarAlerta();
	}
	
	@GetMapping()
	public Page<Alerta> pesquisar(AlertaFilter alertaFilter, Pageable pageable){
		return alertaService.pesquisar(alertaFilter, pageable);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Alerta> porId(@PathVariable int id){
		Alerta alerta = alertaService.find(id);
		return ResponseEntity.ok().body(alerta);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody Alerta alerta){
		alerta = alertaService.insert(alerta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
				.buildAndExpand(alerta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Alerta> update(@PathVariable Integer id, @RequestBody Alerta alerta){
		try {
			Alerta alertaSalvo = alertaService.update(id, alerta);
			return ResponseEntity.ok(alertaSalvo);
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		alertaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
