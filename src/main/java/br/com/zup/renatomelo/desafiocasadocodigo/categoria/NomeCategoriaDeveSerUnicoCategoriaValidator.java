package br.com.zup.renatomelo.desafiocasadocodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NomeCategoriaDeveSerUnicoCategoriaValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCategoriaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object request, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        NovaCategoriaRequest novaCategoriaRequest = (NovaCategoriaRequest) request;

        Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(novaCategoriaRequest.getNome());

        if(possivelCategoria.isPresent()) {
            errors.rejectValue("nome", null, "A categoria " + novaCategoriaRequest.getNome() + " ja esta cadastrada");
        }
    }
}
