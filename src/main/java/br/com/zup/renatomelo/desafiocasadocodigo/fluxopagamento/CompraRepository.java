package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    DetalhesCompraProjection getById(Long id);
}
