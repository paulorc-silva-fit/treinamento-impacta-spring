package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.model.Professor;
import app.repository.ProfessorRepository;
import app.service.ProfessorService;

@Controller
public class ProfessorMonoController {
	@Autowired
	ProfessorRepository profRepo;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/listar-professores")
	public ModelAndView listarProfessores() {
		ModelAndView mvProf = new ModelAndView("listarProfessor");
		Iterable<Professor> professores = profRepo.findAll();
		mvProf.addObject("professores", professores);

		return mvProf;
	}
	
	@RequestMapping("/cadastrar-professor")
	public String cadastrarProfessor() {
		return "cadastrarProfessor";
	}
	
	@RequestMapping(value="/cadastrar-professor", method=RequestMethod.POST)
	public String cadastrarProfessorPost(Professor prof) {
		ProfessorService profService = new ProfessorService();
		
		prof.setSalario(profService.calcularSalario(prof.getQtdHorasTrabalhadas(), prof.getValorHoraTrabalhada()));
		profRepo.save(prof);
		return "redirect:/listar-professores";
	}
	
	@RequestMapping(value="/{matricula}", method=RequestMethod.GET)
	public ModelAndView editarProfessor(@PathVariable("matricula") long matricula) {
		Professor prof = profRepo.findById(matricula);
		ModelAndView mvProf = new ModelAndView("editarProfessor");
		mvProf.addObject("professor", prof);
		
		return mvProf;
	}
	
	@RequestMapping(value="/{matricula}", method=RequestMethod.POST)
	public String editarProfessorPost(Professor prof) {
		ProfessorService profService = new ProfessorService();
		
		prof.setSalario(profService.calcularSalario(prof.getQtdHorasTrabalhadas(), prof.getValorHoraTrabalhada()));
		profRepo.save(prof);
		return "redirect:/listar-professores";
	}
	
	@RequestMapping("/deletar-professor")
	public String deletarProfessor(long matricula) {
		Professor prof = profRepo.findById(matricula);
		profRepo.delete(prof);
		
		return "redirect:/listar-professores";
	}
}
