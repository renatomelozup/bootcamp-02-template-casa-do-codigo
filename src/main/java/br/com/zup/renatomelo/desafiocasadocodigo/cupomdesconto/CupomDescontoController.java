package br.com.zup.renatomelo.desafiocasadocodigo.cupomdesconto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/cupons")
public class CupomDescontoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarCupom(NovoCupomDescontoRequest novoCupomDescontoRequest) {
        CupomDesconto cupom = novoCupomDescontoRequest.toModel();
        entityManager.persist(cupom);

        return ResponseEntity.ok().build();
    }
}
