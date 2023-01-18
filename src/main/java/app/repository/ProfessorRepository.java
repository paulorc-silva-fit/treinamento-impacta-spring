package app.repository;

import org.springframework.data.repository.CrudRepository;

import app.model.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {
	Professor findById(long matricula);
}
