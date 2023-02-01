package br.com.impacta.projeto_final.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginMonoController {	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}