package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.request;

import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.model.Itens;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.model.Livro;
import br.com.zup.renatomelo.desafiocasadocodigo.validator.HasRecord;

import javax.persistence.EntityManager;
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

    public Itens toModel(EntityManager entityManager) {
        Itens itens = new Itens(this.idLivro, this.quantidade);
        entityManager.persist(itens);
        return itens;
    }
}
