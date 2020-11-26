package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long idLivro;
    @NotNull
    @Min(1)
    private Long quantidade;

    public Itens(@NotNull Long idLivro, @NotNull @Min(1) Long quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }
}
