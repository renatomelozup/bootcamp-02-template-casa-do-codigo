package br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.request.NovoEstadoRequest;
import br.com.zup.renatomelo.desafiocasadocodigo.paiseestado.model.Estado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarEstado(@RequestBody @Valid NovoEstadoRequest novoEstadoRequest) {

        Estado novo = novoEstadoRequest.toModel(entityManager);

        entityManager.persist(novo);

        return ResponseEntity.ok().build();
    }
}
