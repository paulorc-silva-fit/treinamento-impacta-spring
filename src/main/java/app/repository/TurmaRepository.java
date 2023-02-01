package app.repository;

import org.springframework.data.repository.CrudRepository;

import app.model.Turma;

public interface TurmaRepository extends CrudRepository<Turma, Long> {
	Turma findById(long codigo);
}