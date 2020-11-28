package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import java.math.BigDecimal;
import java.util.List;

public interface TotalEItensPedidoProjection {
    BigDecimal getTotal();
    List<Itens> getItens();
}
