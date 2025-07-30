package alura.com.forum.hub.ForumHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alura.com.forum.hub.ForumHub.DTO.TopicoDto;
import alura.com.forum.hub.ForumHub.DTO.TopicoForm;
import alura.com.forum.hub.ForumHub.service.TopicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoService topicoService;

	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form) {
		TopicoDto dto = topicoService.cadastrarTopico(form);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@GetMapping
	public Page<TopicoDto> listar(@RequestParam(required = false) String nomeCurso,
			@RequestParam(required = false) Integer ano,
			@PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable paginacao) {
		return topicoService.listarTopicos(nomeCurso, ano, paginacao);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicoDto> detalhar(@PathVariable Long id) {
		TopicoDto dto = topicoService.buscarPorId(id);
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoForm form) {
		TopicoDto dto = topicoService.atualizarTopico(id, form);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		topicoService.excluirTopico(id);
		return ResponseEntity.noContent().build();
	}
}