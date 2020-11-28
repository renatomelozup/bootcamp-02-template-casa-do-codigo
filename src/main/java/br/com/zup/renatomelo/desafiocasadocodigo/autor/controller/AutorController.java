package br.com.zup.renatomelo.desafiocasadocodigo.autor.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.request.NovoAutorRequest;
import br.com.zup.renatomelo.desafiocasadocodigo.autor.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoAutorRequest novoAutorRequest) {

        autorRepository.save(novoAutorRequest.paraAutor());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
