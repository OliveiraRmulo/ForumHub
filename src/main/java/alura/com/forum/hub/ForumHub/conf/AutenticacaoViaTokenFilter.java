package alura.com.forum.hub.ForumHub.conf;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import alura.com.forum.hub.ForumHub.entity.Usuario;
import alura.com.forum.hub.ForumHub.repository.UsuarioRepository;
import alura.com.forum.hub.ForumHub.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		if (token != null && tokenService.isTokenValido(token)) {
			Long idUsuario = tokenService.getIdUsuario(token);
			Usuario usuario = usuarioRepository.findById(idUsuario).get();
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		chain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Bearer "))
			return null;
		return header.substring(7);
	}
}
