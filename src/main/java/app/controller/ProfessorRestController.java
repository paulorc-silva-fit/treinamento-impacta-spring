package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.Professor;
import app.repository.ProfessorRepository;
import app.service.ProfessorService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProfessorRestController {
	@Autowired
	ProfessorRepository profRepo;
	
	// Metodo GET_ALL
	@GetMapping("/professores")
	public List<Professor> listarProfessores() {
		List<Professor> listaProfessores = (List<Professor>) profRepo.findAll();
		return listaProfessores;
	}
	
	// Metodo GET
	@GetMapping("/professor/{matricula}")
	public Professor consultaProfessor(@PathVariable long matricula) {
		Professor prof = profRepo.findById(matricula);
		return prof;
	}

	// Metodo POST
	@PostMapping("/cadastrar-professor")
	public void cadastrarProfessor(@RequestBody Professor prof) {	
		ProfessorService profService = new ProfessorService();
		int qtdHoras = prof.getQtdHorasTrabalhadas();
		float valorHora = prof.getValorHoraTrabalhada();

		prof.setSalario(profService.calcularSalario(qtdHoras, valorHora));
		profRepo.save(prof);
	}
	
	// Metodo DELETE
	@DeleteMapping("/excluir-professor/{matricula}")
	public void excluirProfessor(@PathVariable long matricula) {
		Professor prof = profRepo.findById(matricula);
		profRepo.delete(prof);
	}
	
	// Metodo PUT
	@PutMapping("/editar-professor")
	public void editarProfessor(@RequestBody Professor prof) {
		profRepo.save(prof);
	}
}
