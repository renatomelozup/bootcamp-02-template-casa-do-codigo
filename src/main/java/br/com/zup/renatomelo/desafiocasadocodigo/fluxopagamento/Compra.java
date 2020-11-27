package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import br.com.zup.renatomelo.desafiocasadocodigo.cupomdesconto.CupomDesconto;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.Estado;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.Pais;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String cidade;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cep;
    @NotBlank
    private String telefone;
    @NotNull
    @OneToOne
    private Pais pais;
    @OneToOne
    private Estado estado;
    @NotNull
    @OneToOne
    private Pedido pedido;

    @OneToOne
    @Valid
    private CupomDesconto cupomDesconto;

    public Long getId() {
        return id;
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

    public String getDocumento() {
        return documento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Boolean existeCupom() {
        if(cupomDesconto != null) {
            return true;
        }
        return false;
    }

    public BigDecimal valorCupom() {
        if(existeCupom()) {
            return cupomDesconto.getPercentual().multiply(new BigDecimal(100));
        }
        return null;
    }

    public BigDecimal totalFinal() {
        if(existeCupom()) {
            return this.pedido.getTotal().multiply(valorCupom());
        }

        return this.pedido.getTotal();
    }

    public Compra(@NotBlank @Email String email,
                  @NotBlank String nome,
                  @NotBlank String sobrenome,
                  @NotBlank String documento,
                  @NotBlank String cidade,
                  @NotBlank String endereco,
                  @NotBlank String complemento,
                  @NotBlank String cep,
                  @NotBlank String telefone,
                  Pais pais,
                  Pedido pedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.cidade = cidade;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cep = cep;
        this.telefone = telefone;
        this.pais = pais;
        this.pedido = pedido;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setCupomDesconto(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }
}
