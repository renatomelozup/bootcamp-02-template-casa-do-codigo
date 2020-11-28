package br.com.zup.renatomelo.desafiocasadocodigo.categoria.request;

import br.com.zup.renatomelo.desafiocasadocodigo.categoria.model.Categoria;
import br.com.zup.renatomelo.desafiocasadocodigo.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {
    
    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Categoria toCategoria() {
        return new Categoria(this.nome);
    }
}
