package br.com.zup.renatomelo.desafiocasadocodigo.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.Autor;
import br.com.zup.renatomelo.desafiocasadocodigo.autor.AutorRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.autor.NovoAutorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailDeveSerUnicoAutorValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoAutorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object request, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        NovoAutorRequest novoAutorRequest = (NovoAutorRequest) request;

        Optional<Autor> autor = autorRepository.findByEmail(novoAutorRequest.getEmail());

        if(autor.isPresent()) {
            errors.rejectValue("email",null, "O e-mail " + novoAutorRequest.getEmail() + " já está cadastrado");
        }
    }
}
