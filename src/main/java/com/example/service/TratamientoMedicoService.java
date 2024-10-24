package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.TratamientoMedico;
import com.example.repository.ITratamientoMedicoRepository;

@Service
public class TratamientoMedicoService {
	@Autowired
	private ITratamientoMedicoRepository repository;
	
	public List<TratamientoMedico> listarTratamientoMedicos(){
		return repository.findAll();
	}
	
	public Optional<TratamientoMedico> buscarTratamientoMedico(int id){
		return repository.findById(id);
	}
	
	public void eliminarTratamientoMedico(TratamientoMedico TratamientoMedico){
		repository.delete(TratamientoMedico);
	}
	
	public TratamientoMedico agregarTratamientoMedico(TratamientoMedico TratamientoMedico) {
		return repository.save(TratamientoMedico);
	}
	
	public TratamientoMedico actualizarTratamientoMedico(TratamientoMedico TratamientoMedico) {
		return repository.save(TratamientoMedico);
	}
}
