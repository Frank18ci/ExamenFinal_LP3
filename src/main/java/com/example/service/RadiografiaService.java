package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Radiografia;
import com.example.repository.IRadiografiaRepository;

@Service
public class RadiografiaService {
	@Autowired
	private IRadiografiaRepository repository;
	
	public List<Radiografia> listarRadiografias(){
		return repository.findAll();
	}
	
	public Optional<Radiografia> buscarRadiografia(int id){
		return repository.findById(id);
	}
	
	public void eliminarRadiografia(Radiografia Radiografia){
		repository.delete(Radiografia);
	}
	
	public Radiografia agregarRadiografia(Radiografia Radiografia) {
		return repository.save(Radiografia);
	}
	
	public Radiografia actualizarRadiografia(Radiografia Radiografia) {
		return repository.save(Radiografia);
	}
}
