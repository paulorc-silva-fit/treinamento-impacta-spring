package br.com.impacta.projeto_final.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.impacta.projeto_final.app.model.Usuario;
import br.com.impacta.projeto_final.app.repository.UsuarioRepository;

@Controller
public class UsuarioMonoController {
	@Autowired
	UsuarioRepository userRepo;

	@RequestMapping("/gerenciar-usuarios")
	public ModelAndView listarUsuarios() {
		ModelAndView mvUser = new ModelAndView("gerenciarUsuario");
		Iterable<Usuario> usuarios = userRepo.findAll();
		mvUser.addObject("usuarios", usuarios);

		return mvUser;
	}
	
	@RequestMapping("/cadastrar-usuario")
	public String cadastrarUsuario() {
		return "cadastrarUsuario";
	}
	
	@RequestMapping(value="/cadastrar-usuario", method=RequestMethod.POST)
	public String cadastrarUsuarioPost(Usuario user) {
		user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		userRepo.save(user);
		
		return "redirect:/gerenciar-usuarios";
	}
	
	@RequestMapping(value="/editar-usuario/{login}", method=RequestMethod.GET)
	public ModelAndView editarUsuario(@PathVariable("login") String login) {
		Usuario user = userRepo.findByLogin(login);
		ModelAndView mvUser = new ModelAndView("editarUsuario");
		mvUser.addObject("usuario", user);
		
		return mvUser;
	}
	
	@RequestMapping(value="/editar-usuario/{login}", method=RequestMethod.POST)
	public String editarUsuarioPost(Usuario user) {
		user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		userRepo.save(user);
		
		return "redirect:/gerenciar-usuarios";
	}
	
	@RequestMapping("/deletar-usuario")
	public String deletarUsuario(String login) {
		Usuario user = userRepo.findByLogin(login);
		userRepo.delete(user);
		
		return "redirect:/gerenciar-usuarios";
	}
}