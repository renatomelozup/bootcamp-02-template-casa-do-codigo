package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VerificaDocumentoCpfCNpjValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovoDadoPessoalRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovoDadoPessoalRequest novoDadoPessoalRequest = (NovoDadoPessoalRequest) o;
        if(!novoDadoPessoalRequest.documentoValid()) {
            errors.rejectValue("documento", null, "documento precisa ser cpf ou cnpj");
        }

    }
}
