package alura.com.forum.hub.ForumHub.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String mensagem;

	@ManyToOne
	private Topico topico;

	private LocalDateTime dataCriacao = LocalDateTime.now();

	@ManyToOne
	private Usuario autor;

	private Boolean solucao = false;
}
