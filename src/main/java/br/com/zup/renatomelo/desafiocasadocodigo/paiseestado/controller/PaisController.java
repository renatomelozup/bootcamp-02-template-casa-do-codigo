package br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.model.Pais;
import br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.request.NovoPaisRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarPais(@RequestBody @Valid NovoPaisRequest novoPaisRequest) {

        Pais pais = novoPaisRequest.toModel();

        entityManager.persist(pais);

        return ResponseEntity.ok().build();
    }
}
