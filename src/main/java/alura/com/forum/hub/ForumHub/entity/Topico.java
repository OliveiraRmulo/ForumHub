package alura.com.forum.hub.ForumHub.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Topico {
	

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Resposta> respostas = new ArrayList<>();
}
