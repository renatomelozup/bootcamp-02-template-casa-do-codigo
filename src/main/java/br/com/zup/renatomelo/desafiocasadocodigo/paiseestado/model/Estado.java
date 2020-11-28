package br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @NotNull
    @Valid
    @ManyToOne
    private Pais pais;

    public String getNome() {
        return nome;
    }

    @Deprecated
    public Estado() {
    }

    public Estado(@NotBlank String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public boolean pertenceAPais(Pais pais) {
        return this.pais.equals(pais);
    }
}
