package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import br.com.zup.renatomelo.desafiocasadocodigo.paises.Estado;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.Pais;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @OneToOne
    private Estado estado;
    @NotNull
    @OneToOne
    private Pedido pedido;

    public Long getId() {
        return id;
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
}
