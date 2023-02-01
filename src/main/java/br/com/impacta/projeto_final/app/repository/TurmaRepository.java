package br.com.impacta.projeto_final.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.impacta.projeto_final.app.model.Professor;
import br.com.impacta.projeto_final.app.model.Turma;

public interface TurmaRepository extends CrudRepository<Turma, Long> {
	Iterable<Turma> findByProfessor(Professor professor);
}