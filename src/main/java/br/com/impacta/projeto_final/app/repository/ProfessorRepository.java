package br.com.impacta.projeto_final.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.impacta.projeto_final.app.model.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {
	Professor findById(long matricula);
}
