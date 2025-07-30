package alura.com.forum.hub.ForumHub.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import alura.com.forum.hub.ForumHub.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private String segredo = "chave-secreta";
	private long expiracao = 86400000;

	public String gerarToken(Usuario usuario) {
		return Jwts.builder().setIssuer("API FórumHub").setSubject(usuario.getId().toString()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiracao))
				.signWith(SignatureAlgorithm.HS256, segredo).compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(segredo).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(segredo).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
