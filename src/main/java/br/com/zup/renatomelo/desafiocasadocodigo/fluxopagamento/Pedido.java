package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal total;

    @NotNull
    @NotEmpty
    @Valid
    @OneToMany
    private List<Itens> itens;

    public BigDecimal getTotal() {
        return total;
    }

    public Pedido() {
    }

    public Pedido(@NotNull @DecimalMin(value = "0", inclusive = false) BigDecimal total,
                  @NotNull @NotEmpty @Valid List<Itens> itens) {
        this.total = total;
        this.itens = itens;
    }
}
