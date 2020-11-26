package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class NovoPedidoRequest {

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal total;

    @NotNull
    @NotEmpty
    @Valid
    private List<NovoItemRequest> itens;

    public BigDecimal getTotal() {
        return total;
    }

    public List<NovoItemRequest> getItens() {
        return itens;
    }

    public NovoPedidoRequest() {
    }

    public NovoPedidoRequest(@NotNull @DecimalMin(value = "0", inclusive = false) BigDecimal total,
                             @NotNull @Valid @NotEmpty List<NovoItemRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "NovoPedidoRequest{" +
                "total=" + total +
                ", itens=" + itens +
                '}';
    }
}
