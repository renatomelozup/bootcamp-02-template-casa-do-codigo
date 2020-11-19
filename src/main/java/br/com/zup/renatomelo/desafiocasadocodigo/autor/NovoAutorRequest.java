package br.com.zup.renatomelo.desafiocasadocodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoAutorRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * @param nome = nome do autor não pode ser vazio
     * @param email = email do autor não pode ser vazio e deve ser em formato válido
     * @param descricao = não deve ser vazio e não ultrapassar 400 caracteres
     */
    public NovoAutorRequest(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor paraAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
