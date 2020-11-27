package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import br.com.zup.renatomelo.desafiocasadocodigo.livro.Livro;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.Estado;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.Pais;
import br.com.zup.renatomelo.desafiocasadocodigo.validator.HasRecord;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class NovaCompraRequest {

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

    @NotNull
    @Valid
    private NovoPedidoRequest novoPedidoRequest;

    public String getDocumento() {
        return documento;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public NovoPedidoRequest getNovoPedidoRequest() {
        return novoPedidoRequest;
    }

    @Deprecated
    public NovaCompraRequest() {
    }


    public NovaCompraRequest(@NotBlank @Email String email,
                             @NotBlank String nome,
                             @NotBlank String sobrenome,
                             @NotBlank String documento,
                             @NotBlank String endereco,
                             @NotBlank String complemento,
                             @NotBlank String cidade,
                             @NotNull Long paisId,
                             Long estadoId,
                             @NotBlank String telefone,
                             @NotBlank String cep,
                             @NotNull @Valid NovoPedidoRequest novoPedidoRequest) {
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
        this.novoPedidoRequest = novoPedidoRequest;
    }

    public boolean documentoValid() {
        Assert.hasLength(documento, "não pode validar vazio");

        CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator cnpjValidator = new CNPJValidator();

        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
    }

    public boolean totalValido(EntityManager entityManager) {

        Assert.notNull(novoPedidoRequest, "não pode checar nulo");

        BigDecimal totalCalculado = this.novoPedidoRequest.getItens().stream().map(item -> {
            Livro livro = entityManager.find(Livro.class, item.getIdLivro());
            return livro.getPreco().multiply(new BigDecimal(item.getQuantidade()));
        }).reduce(BigDecimal::add).get();

        //totalCalculado.compareTo(this.getNovoPedidoRequest().getTotal());
        if(totalCalculado.compareTo(this.getNovoPedidoRequest().getTotal()) == 0) {
            return true;
        }
        return false;
        //return totalCalculado.equals(this.novoPedidoRequest.getTotal());
    }

    @Override
    public String toString() {
        return "NovaCompraRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", paisId=" + paisId +
                ", estadoId=" + estadoId +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", novoPedidoRequest=" + novoPedidoRequest +
                '}';
    }

    public Compra toModel(EntityManager entityManager) {

        Pais pais = entityManager.find(Pais.class, this.paisId);

        Pedido pedido = novoPedidoRequest.toModel(entityManager);

        Compra compra = new Compra(this.email,
                this.nome,
                this.sobrenome,
                this.documento,
                this.cidade,
                this.endereco,
                this.complemento,
                this.cep,
                this.telefone,
                pais,
                pedido);

        if(estadoId != null) {
            compra.setEstado(entityManager.find(Estado.class, this.estadoId));
        }

        return compra;
    }

    public boolean temEstado() {
        return estadoId != null;
    }

}
