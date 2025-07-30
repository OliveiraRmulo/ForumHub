package alura.com.forum.hub.ForumHub.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import alura.com.forum.hub.ForumHub.entity.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	boolean existsByTituloAndMensagem(String titulo, String mensagem);

	Page<Topico> findByCursoNome(String nomeCurso, Pageable pageable);

	Page<Topico> findByCursoNomeAndDataCriacaoBetween(String nomeCurso, LocalDateTime inicio, LocalDateTime fim,
			Pageable pageable);
}
