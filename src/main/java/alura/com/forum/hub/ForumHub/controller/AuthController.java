package alura.com.forum.hub.ForumHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alura.com.forum.hub.ForumHub.DTO.LoginRequest;
import alura.com.forum.hub.ForumHub.DTO.TokenDto;
import alura.com.forum.hub.ForumHub.entity.Usuario;
import alura.com.forum.hub.ForumHub.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginRequest request) {
		UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getSenha());

		Authentication autenticacao = authManager.authenticate(dadosLogin);
		String token = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

		return ResponseEntity.ok(new TokenDto(token, "Bearer"));
	}
}
