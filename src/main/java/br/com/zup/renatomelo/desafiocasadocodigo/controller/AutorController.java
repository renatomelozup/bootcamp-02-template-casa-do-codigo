package br.com.zup.renatomelo.desafiocasadocodigo.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.AutorRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.autor.NovoAutorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EmailDeveSerUnicoAutorValidator emailDeveSerUnicoAutorValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(emailDeveSerUnicoAutorValidator);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoAutorRequest novoAutorRequest) {

        autorRepository.save(novoAutorRequest.paraAutor());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
