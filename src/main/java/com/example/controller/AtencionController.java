package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Atencion;
import com.example.service.AtencionService;
import com.example.service.MedicoService;
import com.example.service.PacienteService;

@Controller
@RequestMapping("atencion/")
public class AtencionController {
	@Autowired
	private AtencionService atencionService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private MedicoService medicoService;
	@GetMapping("/listar")
	public String getList(Model model) {
		model.addAttribute("listaAtencion", atencionService.listarAtenciones());
		return "listaAtencion";
	}
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("atencion", new Atencion());
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		model.addAttribute("listaMedico", medicoService.listarMedicos());
		return "agregarAtencion";
	}
	@PostMapping("/agregar")
	public String AgregarAtencion(Model model, Atencion Atencion) {
		try {
			Atencion AtencionA =  atencionService.agregarAtencion(Atencion);
			if(AtencionA == null) {
				throw new Exception();
			}
		}catch (Exception e) {
			model.addAttribute("atencion", Atencion);
			model.addAttribute("error", "Error al agregar Atencion");
			model.addAttribute("listaPaciente", pacienteService.listarPacientes());
			model.addAttribute("listaMedico", medicoService.listarMedicos());
			return "agregarAtencion";
		}
		return "redirect:/atencion/listar";
		
	}
}
