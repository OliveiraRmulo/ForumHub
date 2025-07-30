package alura.com.forum.hub.ForumHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import alura.com.forum.hub.ForumHub.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByNome(String nome);
}
