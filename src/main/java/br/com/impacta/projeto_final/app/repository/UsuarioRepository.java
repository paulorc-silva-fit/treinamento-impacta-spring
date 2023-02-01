package br.com.impacta.projeto_final.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.impacta.projeto_final.app.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	Usuario findByLogin(String login);
}
