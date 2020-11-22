package br.com.zup.renatomelo.desafiocasadocodigo.autor;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    @NotNull
    @PastOrPresent
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    /**
     * @deprecated  apenas usado pelo hibernate
     */
    @Deprecated
    Autor() {
    }

    /**
     * @param nome = nome do autor não pode ser vazio
     * @param email = email do autor não pode ser vazio e deve ser em formato válido
     * @param descricao = não deve ser vazio e não ultrapassar 400 caracteres
     */
    public Autor(@NotBlank String nome, @Email @NotBlank String email, @NotBlank @Size(max = 400) String descricao) {

        Assert.hasText(nome, "nome não pode ser vazio");
        Assert.hasText(email, "email não pode ser vazio e deve ter formato valido");
        Assert.hasText(descricao, "não pode ser vazio e deve ser menor que 400 caracteres");

        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }
}
