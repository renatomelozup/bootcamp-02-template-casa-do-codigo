package br.com.zup.renatomelo.desafiocasadocodigo.cupomdesconto.repository;

import br.com.zup.renatomelo.desafiocasadocodigo.cupomdesconto.model.CupomDesconto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomDescontoRepository extends JpaRepository<CupomDesconto, Long> {
    CupomDesconto findByCodigo(String codigoCupom);
}
