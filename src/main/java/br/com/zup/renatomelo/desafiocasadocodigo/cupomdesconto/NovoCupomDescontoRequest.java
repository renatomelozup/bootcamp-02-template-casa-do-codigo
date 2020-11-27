package br.com.zup.renatomelo.desafiocasadocodigo.cupomdesconto;

import br.com.zup.renatomelo.desafiocasadocodigo.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoCupomDescontoRequest {

    @NotBlank
    @UniqueValue(domainClass = CupomDesconto.class, fieldName = "codigo")
    private String codigo;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal percentual;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate validade;

    @Deprecated
    public NovoCupomDescontoRequest() {
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public LocalDate getValidade() {
        return validade;
    }

    /**
     * @param codigo = codigo do cupom não pode ser em branco
     * @param percentual = percentual em casas decimais e não pode ser em branco
     * @param validade = data no futoro até quando é valido o cupom
     */
    public NovoCupomDescontoRequest(@NotBlank String codigo,
                                    @NotNull BigDecimal percentual,
                                    @Future LocalDate validade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }

    public CupomDesconto toModel() {
        System.out.println("" + this.codigo +  this.percentual + this.validade);
        return new CupomDesconto(this.codigo, this.percentual, this.validade);
    }
}
