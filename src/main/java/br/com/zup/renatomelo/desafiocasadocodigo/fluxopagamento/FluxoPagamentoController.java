package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/compras")
public class FluxoPagamentoController {

    @Autowired
    private PaisTemEstadoValidator paisTemEstadosValidator;

    @Autowired
    private VerificaDocumentoCpfCNpjValidator verificaDocumentoCpfCNpjValidator;

    @Autowired
    private VerificaTotalValidator verificaTotalValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(verificaDocumentoCpfCNpjValidator, paisTemEstadosValidator, verificaTotalValidator);
    }

    @PostMapping
    public ResponseEntity<?> recebeDados(@RequestBody @Valid NovaCompraRequest novaCompraRequest) {

        return ResponseEntity.ok(novaCompraRequest.toString());
    }
}
