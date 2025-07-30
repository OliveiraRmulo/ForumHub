package alura.com.forum.hub.ForumHub.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank
	private String email;

	@NotBlank
	private String senha;
}
