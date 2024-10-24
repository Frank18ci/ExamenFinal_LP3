package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Alergia;
import com.example.repository.IAlergiaRepository;

@Service
public class AlergiaService {
	@Autowired
	private IAlergiaRepository repository;
	
	public List<Alergia> listarAlergias(){
		return repository.findAll();
	}
	
	public Optional<Alergia> buscarAlergia(int id){
		return repository.findById(id);
	}
	
	public void eliminarAlergia(Alergia alergia){
		repository.delete(alergia);
	}
	
	public Alergia agregarAlergia(Alergia alergia) {
		return repository.save(alergia);
	}
	
	public Alergia actualizarAlergia(Alergia alergia) {
		return repository.save(alergia);
	}
}
