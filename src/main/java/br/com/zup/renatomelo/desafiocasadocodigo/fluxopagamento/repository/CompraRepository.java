package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.repository;

import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.response.DetalhesCompraProjection;
import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    Optional<DetalhesCompraProjection> getById(Long id);
}
