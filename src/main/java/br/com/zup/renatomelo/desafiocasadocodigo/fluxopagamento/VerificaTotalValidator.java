package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class VerificaTotalValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;
        if(!novaCompraRequest.totalValido(manager)) {
            errors.rejectValue("novoPedidoRequest",
                    null,
                    "o valor total informado não corresponde com o valor total dos itens cadastrados");
        }
    }
}
