package com.example.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Paciente;
import com.example.service.PacienteService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("paciente/")
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping({"/listar", "/"})
	public String getList(Model model) {
		model.addAttribute("listaPaciente", pacienteService.listarPacientes());
		return "listaPaciente";
	}
	@GetMapping("/agregar")
	public String getAgregar(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "agregarPaciente";
	}
	@PostMapping("/agregar")
	public String AgregarPaciente(Model model, Paciente paciente) {
		try {
			Paciente pacienteA = pacienteService.agregarPaciente(paciente);
			 if (pacienteA == null) {
		            throw new Exception("El paciente no pudo ser agregado.");
		    }
		}catch (Exception e) {
			model.addAttribute("paciente", paciente);
			model.addAttribute("error", "Error al agregar paciente");
			return "agregarPaciente";
		}
		return "redirect:/paciente/listar";
		
	}
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	@RequestMapping(value = "/PacienteReport", method = RequestMethod.GET)
	@ResponseBody
	public void PacienteReport(HttpServletResponse response) throws JRException, IOException, SQLException {
	    try (Connection connection = jdbcTemplate.getDataSource().getConnection();
	         InputStream jasperStream = this.getClass().getResourceAsStream("/reporte/ReportePacientes.jasper");
	         OutputStream outputStream = response.getOutputStream()) {
	         
	        if (jasperStream == null) {
	            throw new FileNotFoundException("El archivo ReportePacientes.jasper no fue encontrado en la ruta especificada.");
	        }
	        Map<String, Object> params = new HashMap<String, Object>();
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	        JasperPrint jasperPrint = getReport(pacienteService.listarPacientes());
	        response.setContentType("application/x-pdf");
	        response.setHeader("Content-disposition", "inline; filename=pacientes_report.pdf");
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	    }
	}
	public byte[] exportToPdf(List<Paciente> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }


    private JasperPrint getReport(List<Paciente> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("petsData", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:Black_A4.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }

}