package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import br.com.zup.renatomelo.desafiocasadocodigo.paises.Estado;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.NomeEstadoProjection;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.Pais;

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
