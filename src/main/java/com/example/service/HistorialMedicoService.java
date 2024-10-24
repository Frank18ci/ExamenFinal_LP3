package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Atencion;
import com.example.model.HistorialMedico;
import com.example.model.Paciente;
import com.example.repository.IHistorialMedicoRepository;

@Service
public class HistorialMedicoService {
	@Autowired
	private IHistorialMedicoRepository repository;
	@Autowired 
	private PacienteService pacienteService;
	@Autowired
	private AtencionService atencionService;
	public List<HistorialMedico> listarHistorialMedicos(){
		return repository.findAll();
	}
	
	public Optional<HistorialMedico> buscarHistorialMedico(int id){
		return repository.findById(id);
	}
	
	public void eliminarHistorialMedico(HistorialMedico HistorialMedico){
		Paciente paciente = HistorialMedico.getPaciente();
		if(paciente.getAtenciones() != null) {
			for(Atencion a : paciente.getAtenciones()) {
				atencionService.eliminarAtencion(a);
			}
		}
		paciente.setAtenciones(null);
		paciente.setHistorialMedico(null);
		pacienteService.actualizarPaciente(paciente);
		repository.delete(HistorialMedico);
	}
	
	public HistorialMedico agregarHistorialMedico(HistorialMedico HistorialMedico) {
		HistorialMedico historialMedicoS = repository.save(HistorialMedico);
		Paciente paciente = historialMedicoS.getPaciente();
		paciente.setHistorialMedico(historialMedicoS);
		paciente = pacienteService.actualizarPaciente(paciente);
		return historialMedicoS;
	}
	
	public HistorialMedico actualizarHistorialMedico(HistorialMedico HistorialMedico) {
		return repository.save(HistorialMedico);
	}
}
