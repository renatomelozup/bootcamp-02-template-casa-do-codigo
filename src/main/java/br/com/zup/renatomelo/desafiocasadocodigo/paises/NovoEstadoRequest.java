package br.com.zup.renatomelo.desafiocasadocodigo.paises;

import br.com.zup.renatomelo.desafiocasadocodigo.validator.HasRecord;
import br.com.zup.renatomelo.desafiocasadocodigo.validator.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;

    @NotNull
    @HasRecord(domainClass = Pais.class, fieldName = "id")
    private Long paisId;

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    @Deprecated
    public NovoEstadoRequest() {
    }

    public NovoEstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }


    public Estado toModel(EntityManager entityManager) {
        Pais pais = entityManager.find(Pais.class, this.paisId);

        Assert.notNull(pais, "pais não encontrado");

        return new Estado(this.nome, pais);
    }
}
