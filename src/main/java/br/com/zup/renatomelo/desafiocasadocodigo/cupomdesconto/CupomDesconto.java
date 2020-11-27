package br.com.zup.renatomelo.desafiocasadocodigo.cupomdesconto;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CupomDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String codigo;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal percentual;
    @Future
    private LocalDate validade;

    @Deprecated
    public CupomDesconto() {
    }

    /**
     * @param codigo = codigo do cupom não pode ser em branco
     * @param percentual = percentual em casas decimais e não pode ser em branco
     * @param validade = data no futoro até quando é valido o cupom
     */
    public CupomDesconto(@NotBlank String codigo,
                         @NotNull @DecimalMin(value = "0.01") BigDecimal percentual,
                         @Future @NotNull LocalDate validade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }
}
