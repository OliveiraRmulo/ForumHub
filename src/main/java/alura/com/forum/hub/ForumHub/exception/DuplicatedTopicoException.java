package alura.com.forum.hub.ForumHub.exception;

public class DuplicatedTopicoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicatedTopicoException(String message) {
		super(message);
	}
}
