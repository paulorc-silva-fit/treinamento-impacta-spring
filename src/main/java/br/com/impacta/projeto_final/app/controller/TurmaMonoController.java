package br.com.impacta.projeto_final.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.impacta.projeto_final.app.model.Professor;
import br.com.impacta.projeto_final.app.model.Turma;
import br.com.impacta.projeto_final.app.repository.ProfessorRepository;
import br.com.impacta.projeto_final.app.repository.TurmaRepository;

@Controller
public class TurmaMonoController {
	@Autowired
	ProfessorRepository profRepo;

	@Autowired
	TurmaRepository turmaRepo;
	
	@RequestMapping(value="/cadastrar-turma/{matricula}", method=RequestMethod.GET)
	public ModelAndView cadastrarTurma(@PathVariable("matricula") long matricula) {
		Professor prof = profRepo.findById(matricula);
		ModelAndView mvProf = new ModelAndView("cadastrarTurma");
		
		Iterable<Turma> turmas = turmaRepo.findByProfessor(prof);
		mvProf.addObject("turmas", turmas);
		mvProf.addObject("professor", prof);
		return mvProf;
	}
	
	@RequestMapping(value="/cadastrar-turma/{matricula}", method=RequestMethod.POST)
	public String cadastrarTurmaPost(@PathVariable("matricula") long matricula, Turma turma) {	
		turma.setProfessor(profRepo.findById(matricula));
		turmaRepo.save(turma);
		
		return "redirect:/cadastrar-turma/{matricula}";
	}
}