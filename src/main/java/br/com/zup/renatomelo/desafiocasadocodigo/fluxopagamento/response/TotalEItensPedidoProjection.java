package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.response;

import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.model.Itens;

import java.math.BigDecimal;
import java.util.List;

public interface TotalEItensPedidoProjection {
    BigDecimal getTotal();
    List<Itens> getItens();
}
