package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.HistorialMedico;
import com.example.service.HistorialMedicoService;
import com.example.service.PacienteService;

@Controller
@RequestMapping("historialMedico/")
public class HistorialMedicoController {
	@Autowired
	private HistorialMedicoService historialMedicoService;
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping("/listar")
	public String getList(Model model) {
		model.addAttribute("listaHistorialMedico", historialMedicoService.listarHistorialMedicos());
		return "listaHistorialMedico";
	}
	@GetMapping("/informacion")
	public String getInfo(Model model, int id) {
		model.addAttribute("historialMedico", historialMedicoService.buscarHistorialMedico(id).get());
		return "informacionHistorialMedico";
	}
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("historialMedico", new HistorialMedico());
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		return "agregarHistorialMedico";
	}
	@PostMapping("/agregar")
	public String AgregarHistorialMedico(Model model, HistorialMedico HistorialMedico) {
		try {
			HistorialMedico historialMedicoA =  historialMedicoService.agregarHistorialMedico(HistorialMedico);
			if(historialMedicoA == null) {
				throw new Exception();
			}
		}catch (Exception e) {
			model.addAttribute("historialMedico", HistorialMedico);
			model.addAttribute("error", "Error al agregar HistorialMedico");
			model.addAttribute("listaPaciente", pacienteService.listarPacientes());
			return "agregarHistorialMedico";
		}
		return "redirect:/historialMedico/listar";
	}
	@GetMapping("/actualizar")
	public String getActualizar(Model model, int id) {
		Optional<HistorialMedico> historialMedico = historialMedicoService.buscarHistorialMedico(id);
		model.addAttribute("historialMedico", historialMedico.get());
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		return "actualizarHistorialMedico";
	}
	@PostMapping("/actualizar")
	public String ActualizarHistorialMedico(Model model, HistorialMedico HistorialMedico) {
		try {
			HistorialMedico historialMedicoA =  historialMedicoService.actualizarHistorialMedico(HistorialMedico);
			if(historialMedicoA == null) {
				throw new Exception();
			}
		}catch (Exception e) {
			model.addAttribute("historialMedico", HistorialMedico);
			model.addAttribute("error", "Error al actualizar HistorialMedico");
			model.addAttribute("listaPaciente", pacienteService.listarPacientes());
			return "actualizarHistorialMedico";
		}
		return "redirect:/historialMedico/listar";
	}
	@GetMapping("/eliminar")
	public String eliminar(int id) {
		Optional<HistorialMedico> historialMedico = historialMedicoService.buscarHistorialMedico(id);
		 if(historialMedico.isPresent()) {
		        HistorialMedico hm = historialMedico.get();
		        historialMedicoService.eliminarHistorialMedico(hm);
		    }
		
		return "redirect:/historialMedico/listar";
	}
}
