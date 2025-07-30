package alura.com.forum.hub.ForumHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import alura.com.forum.hub.ForumHub.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

	Optional<Curso> findByNome(String nome);

}
