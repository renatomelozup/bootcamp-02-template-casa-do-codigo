package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.validators;

import br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento.request.NovaCompraRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VerificaDocumentoCpfCNpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;
        if(!novaCompraRequest.documentoValid()) {
            errors.rejectValue("documento", null, "documento precisa ser cpf ou cnpj");
        }

    }
}
