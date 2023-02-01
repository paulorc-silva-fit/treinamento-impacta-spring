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

import app.model.Turma;
import app.repository.ProfessorRepository;
import app.repository.TurmaRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TurmaRestController {
	@Autowired
	ProfessorRepository profRepo;
	
	@Autowired
	TurmaRepository turmaRepo;
	
	// Metodo GET_ALL
	@GetMapping("/turmas")
	public List<Turma> listarTurmas() {
		List<Turma> listaTurmas = (List<Turma>) turmaRepo.findAll();
		return listaTurmas;
	}
	
	// Metodo GET
	@GetMapping("/turma/{codigo}")
	public Turma consultaTurma(@PathVariable long codigo) {
		Turma turma= turmaRepo.findById(codigo);
		return turma;
	}

	// Metodo POST
	@PostMapping("/cadastrar-turma/{matricula}")
	public void cadastrarTurma(@RequestBody Turma turma, @PathVariable long matricula) {	
		turma.setProfessor(profRepo.findById(matricula));
		turmaRepo.save(turma);
	}
	
	// Metodo DELETE
	@DeleteMapping("/excluir-turma/{codigo}")
	public void excluirTurma(@PathVariable long codigo) {
		Turma turma = turmaRepo.findById(codigo);
		turmaRepo.delete(turma);
	}
	
	// Metodo PUT
	@PutMapping("/editar-turma/{matricula}")
	public void editarTurma(@RequestBody Turma turma, @PathVariable long matricula) {
		turma.setProfessor(profRepo.findById(matricula));
		turmaRepo.save(turma);
	}
}
