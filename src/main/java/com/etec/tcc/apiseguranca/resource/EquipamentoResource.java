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

import com.etec.tcc.apiseguranca.model.Equipamento;
import com.etec.tcc.apiseguranca.service.EquipamentoService;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoResource {
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@GetMapping("/todos")
	public List<Equipamento> listarTodosEquipamentos(){
		return equipamentoService.listarEquipamentos();
	}
	
	@GetMapping()
	public Page<Equipamento> pesquisar(@RequestParam(required=false, 
			defaultValue = "")String nome, Pageable pageable) {
		return equipamentoService.pesquisar(nome, pageable);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Equipamento> porId(@PathVariable Integer id){
		Equipamento equipamento = equipamentoService.find(id);
		return ResponseEntity.ok().body(equipamento);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Equipamento equipamento){
		equipamento = equipamentoService.insert(equipamento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{Id}").buildAndExpand(equipamento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		equipamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("{id}")
	public ResponseEntity<Equipamento> update(@PathVariable Integer id, 
			@RequestBody Equipamento equipamento){
		try {
			Equipamento equipamentoSalvo = equipamentoService.update(id, equipamento);
			return ResponseEntity.ok(equipamentoSalvo);
		} catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build();
		}
	}
}
