package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.response;

import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.model.Pedido;
import br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.response.NomeEstadoProjection;
import br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.model.Pais;

import java.math.BigDecimal;

public interface DetalhesCompraProjection {

    String getEmail();
    String getNome();
    String getSobrenome();
    String getDocumento();
    String getCidade();
    String getEndereco();
    String getComplemento();
    String getCep();
    String getTelefone();
    Pais getPais();
    NomeEstadoProjection getEstado();
    Pedido getPedido();
    Boolean getExisteCupom();
    BigDecimal getValorCupom();
    BigDecimal getTotalFinal();
}
