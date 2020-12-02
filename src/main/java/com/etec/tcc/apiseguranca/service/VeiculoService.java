package com.etec.tcc.apiseguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.etec.tcc.apiseguranca.model.Veiculo;
import com.etec.tcc.apiseguranca.repository.VeiculoRepository;
import com.etec.tcc.apiseguranca.service.exceptions.ObjectNotFoundException;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public List<Veiculo> listarVeiculos(){
		return veiculoRepository.findAll();
	}
	
	public Page<Veiculo> pesquisar(String nome, Pageable pageable){
		return veiculoRepository.findByNomeContaining(nome, pageable);
	}
	
	public Veiculo find(Integer id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		return veiculo.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + "Tipo: " + Veiculo.class.getName())); 
	}
	
	public Veiculo insert(Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}
	
	public Veiculo update(int id, Veiculo veiculo) {
		Veiculo veiculoSalvo = find(id);
		
		BeanUtils.copyProperties(veiculo, veiculoSalvo, "id");
		
		return veiculoRepository.save(veiculoSalvo);
	}
	
	public void delete(Integer id) {
		find(id);
		veiculoRepository.deleteById(id);
	}
}
