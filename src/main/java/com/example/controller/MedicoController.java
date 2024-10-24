package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Medico;
import com.example.service.MedicoService;

@Controller
@RequestMapping("medico/")
public class MedicoController {
	@Autowired
	private MedicoService medicoService;
	
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("medico", new Medico());
		return "agregarMedico";
	}
	@PostMapping("/agregar")
	public String AgregarPaciente(Model model, Medico medico) {
		try {
			Medico medicoA = medicoService.agregarMedico(medico);
			 if (medicoA == null) {
		            throw new Exception("El medico no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("paciente", medico);
			model.addAttribute("error", "Error al agregar medico");
			return "agregarMedico";
		}
		return "redirect:/atencion/agregar";
		
	}
}
