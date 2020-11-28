package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    Optional<DetalhesCompraProjection > getById(Long id);
}
