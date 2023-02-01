package br.com.impacta.projeto_final.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig { 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http // objeto do tipo HttpSecurity 
			.authorizeRequests() // Autorização para qualquer Requisição
			.antMatchers(HttpMethod.POST).permitAll()
			.antMatchers(HttpMethod.PUT).permitAll()
			.antMatchers(HttpMethod.DELETE).permitAll()
			.anyRequest().authenticated() // Obriga o usuário estar logado
			.and() // Conexão de instruções
			.formLogin()//Indica que terá uma pagina com formulario de Login
			.loginPage("/login")// Indica a pagina HTML que será usada para o Login
			.defaultSuccessUrl("/") // Indica a pagina que será apresentada apos a realização do Login
			.permitAll() // Todos os Usuarios logodos possuem permissão para acessar a Index
			.and() // Conexão de instruções
			.logout()//Indica que a aplicação terá logout
			.permitAll()
			.and()
			.csrf().disable();
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}