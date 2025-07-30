package alura.com.forum.hub.ForumHub.DTO;

import java.time.LocalDateTime;

import alura.com.forum.hub.ForumHub.entity.Topico;
import lombok.Data;

@Data
public class TopicoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String status;
	private String nomeAutor;
	private String nomeCurso;

	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.status = topico.getStatus().name();
		this.nomeAutor = topico.getAutor().getNome();
		this.nomeCurso = topico.getCurso().getNome();
	}
}
