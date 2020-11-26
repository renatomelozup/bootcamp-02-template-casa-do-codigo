package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import br.com.zup.renatomelo.desafiocasadocodigo.livro.Livro;
import br.com.zup.renatomelo.desafiocasadocodigo.validator.HasRecord;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NovoItemRequest {

    @NotNull
    @HasRecord(domainClass = Livro.class, fieldName = "id")
    private Long idLivro;

    @NotNull
    @Min(1)
    private Long quantidade;

    public Long getIdLivro() {
        return idLivro;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    @Deprecated
    public NovoItemRequest() {
    }

    public NovoItemRequest(@NotNull Long idLivro, @NotNull @Min(1) Long quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "NovoItemRequest{" +
                "idLivro=" + idLivro +
                ", quantidade=" + quantidade +
                '}';
    }
}
