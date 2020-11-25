package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import br.com.zup.renatomelo.desafiocasadocodigo.paises.Estado;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.Pais;
import br.com.zup.renatomelo.desafiocasadocodigo.validator.HasRecord;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoDadoPessoalRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @HasRecord(domainClass = Pais.class, fieldName = "id")
    private Long paisId;

    @HasRecord(domainClass = Estado.class, fieldName = "id")
    private Long estadoId;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public String getDocumento() {
        return documento;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public NovoDadoPessoalRequest(@NotBlank @Email String email,
                                  @NotBlank String nome,
                                  @NotBlank String sobrenome,
                                  @NotBlank String documento,
                                  @NotBlank String endereco,
                                  @NotBlank String complemento,
                                  @NotBlank String cidade,
                                  @NotNull Long paisId,
                                  Long estadoId,
                                  @NotBlank String telefone,
                                  @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public boolean documentoValid() {
        Assert.hasLength(documento, "não pode validar vazio");

        CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator cnpjValidator = new CNPJValidator();

        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
    }
}
