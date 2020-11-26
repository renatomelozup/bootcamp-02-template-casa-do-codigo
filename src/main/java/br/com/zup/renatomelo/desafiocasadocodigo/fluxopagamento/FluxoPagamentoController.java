package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(verificaDocumentoCpfCNpjValidator, paisTemEstadosValidator, verificaTotalValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> recebeDados(@RequestBody @Valid NovaCompraRequest novaCompraRequest,
                                         UriComponentsBuilder uriComponentsBuilder) {

        Compra compra = novaCompraRequest.toModel(entityManager);

        entityManager.persist(compra);

        URI uri = uriComponentsBuilder.path("/compras/{id}").buildAndExpand(compra.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
