package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Equipamento;
import com.etec.tcc.apiseguranca.repository.EquipamentoRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class EquipamentoService {

	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	public List<Equipamento> listarEquipamentos(){
		return equipamentoRepository.findAll();
	}
	
	public Page<Equipamento> pesquisar(String nome, Pageable pageable){
		return equipamentoRepository.findByNomeContaining(nome, pageable);
	}
	
	public Equipamento find(Integer id) {
		Optional<Equipamento> equipamento = equipamentoRepository.findById(id);
		return equipamento.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + Equipamento.class.getName())); 
	}
	
	public Equipamento insert(Equipamento equipamento) {
		return equipamentoRepository.save(equipamento);
	}
	
	public Equipamento update(int id, Equipamento equipamento) {
		Equipamento equipamentoSalvo = find(id);
		
		BeanUtils.copyProperties(equipamento, equipamentoSalvo, "id");
		
		return equipamentoRepository.save(equipamentoSalvo);
	}
	
	public void delete(Integer id) {
		find(id);
		equipamentoRepository.deleteById(id);
	}
}
