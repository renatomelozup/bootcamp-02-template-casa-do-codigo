package br.com.zup.renatomelo.desafiocasadocodigo.cupomdesconto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, Long> {
    CupomDesconto findByCodigo(String codigoCupom);
}
