package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.cupomdesconto.repository.CupomDescontoRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.request.NovaCompraRequest;
import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.validators.PaisTemEstadoValidator;
import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.validators.VerificaDocumentoCpfCNpjValidator;
import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.validators.VerificaTotalValidator;
import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.model.Compra;
import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/compras")
public class FluxoPagamentoController {

    @Autowired
    private PaisTemEstadoValidator paisTemEstadosValidator;

    @Autowired
    private VerificaDocumentoCpfCNpjValidator verificaDocumentoCpfCNpjValidator;

    @Autowired
    private VerificaTotalValidator verificaTotalValidator;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CupomDescontoRepository cupomDescontoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(verificaDocumentoCpfCNpjValidator, paisTemEstadosValidator, verificaTotalValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> recebeDados(@RequestBody @Valid NovaCompraRequest novaCompraRequest,
                                         UriComponentsBuilder uriComponentsBuilder) {

        Compra compra = novaCompraRequest.toModel(entityManager, cupomDescontoRepository);

        entityManager.persist(compra);

        URI uri = uriComponentsBuilder.path("/compras/{id}").buildAndExpand(compra.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalheCompra(@PathVariable Long id) {
        return new ResponseEntity<>(compraRepository.getById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "compra não encontrada");
                }), HttpStatus.OK);
    }
}
