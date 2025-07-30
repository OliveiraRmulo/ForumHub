package alura.com.forum.hub.ForumHub.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TopicoForm {

	@NotBlank
	private String titulo;

	@NotBlank
	private String mensagem;

	@NotBlank
	private String nomeCurso;

	@NotBlank
	private String nomeAutor;
}
