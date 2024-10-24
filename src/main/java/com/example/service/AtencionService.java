package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Atencion;
import com.example.repository.IAtencionRepository;

@Service
public class AtencionService {
	@Autowired
	private IAtencionRepository repository;
	
	public List<Atencion> listarAtenciones(){
		return repository.findAll();
	}
	
	public Optional<Atencion> buscarAtencion(int id){
		return repository.findById(id);
	}
	
	public void eliminarAtencion(Atencion Atencion){
		repository.delete(Atencion);
	}
	
	public Atencion agregarAtencion(Atencion Atencion) {
		return repository.save(Atencion);
	}
	
	public Atencion actualizarAtencion(Atencion Atencion) {
		return repository.save(Atencion);
	}
}
