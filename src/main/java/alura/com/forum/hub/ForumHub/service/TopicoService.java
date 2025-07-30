package alura.com.forum.hub.ForumHub.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import alura.com.forum.hub.ForumHub.DTO.TopicoDto;
import alura.com.forum.hub.ForumHub.DTO.TopicoForm;
import alura.com.forum.hub.ForumHub.entity.Curso;
import alura.com.forum.hub.ForumHub.entity.Topico;
import alura.com.forum.hub.ForumHub.entity.Usuario;
import alura.com.forum.hub.ForumHub.exception.DuplicatedTopicoException;
import alura.com.forum.hub.ForumHub.exception.ResourceNotFoundException;
import alura.com.forum.hub.ForumHub.repository.CursoRepository;
import alura.com.forum.hub.ForumHub.repository.TopicoRepository;
import alura.com.forum.hub.ForumHub.repository.UsuarioRepository;

@Service
public class TopicoService {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public TopicoDto cadastrarTopico(TopicoForm form) {

		if (topicoRepository.existsByTituloAndMensagem(form.getTitulo(), form.getMensagem())) {
			throw new DuplicatedTopicoException("Tópico com mesmo título e mensagem já existe");
		}

		Curso curso = cursoRepository.findByNome(form.getNomeCurso())
				.orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

		Usuario autor = usuarioRepository.findByNome(form.getNomeAutor())
				.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

		Topico topico = new Topico();
		topico.setTitulo(form.getTitulo());
		topico.setMensagem(form.getMensagem());
		topico.setCurso(curso);
		topico.setAutor(autor);

		topicoRepository.save(topico);

		return new TopicoDto(topico);
	}

	public Page<TopicoDto> listarTopicos(String nomeCurso, Integer ano, Pageable paginacao) {
		Page<Topico> topicos;

		if (nomeCurso != null && ano != null) {
			LocalDateTime inicio = LocalDateTime.of(ano, 1, 1, 0, 0);
			LocalDateTime fim = inicio.plusYears(1);
			topicos = topicoRepository.findByCursoNomeAndDataCriacaoBetween(nomeCurso, inicio, fim, paginacao);
		} else if (nomeCurso != null) {
			topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
		} else {
			topicos = topicoRepository.findAll(paginacao);
		}

		return topicos.map(TopicoDto::new);
	}

	public TopicoDto buscarPorId(Long id) {
		Topico topico = topicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tópico com ID " + id + " não encontrado"));

		return new TopicoDto(topico);
	}

	public TopicoDto atualizarTopico(Long id, TopicoForm form) {

		Topico topico = topicoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tópico com ID " + id + " não encontrado"));

		if (topicoRepository.existsByTituloAndMensagem(form.getTitulo(), form.getMensagem())
				&& (!topico.getTitulo().equals(form.getTitulo()) || !topico.getMensagem().equals(form.getMensagem()))) {
			throw new DuplicatedTopicoException("Outro tópico com mesmo título e mensagem já existe");
		}

		Curso curso = cursoRepository.findByNome(form.getNomeCurso())
				.orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

		Usuario autor = usuarioRepository.findByNome(form.getNomeAutor())
				.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));

		topico.setTitulo(form.getTitulo());
		topico.setMensagem(form.getMensagem());
		topico.setCurso(curso);
		topico.setAutor(autor);

		return new TopicoDto(topicoRepository.save(topico));
	}

	public void excluirTopico(Long id) {
		if (!topicoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Tópico com ID " + id + " não encontrado");
		}
		topicoRepository.deleteById(id);
	}
}
