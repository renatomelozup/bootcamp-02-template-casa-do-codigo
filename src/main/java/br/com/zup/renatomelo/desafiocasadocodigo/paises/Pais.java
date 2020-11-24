package br.com.zup.renatomelo.desafiocasadocodigo.paises;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    public Pais(@NotBlank String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Pais() {
    }
}
