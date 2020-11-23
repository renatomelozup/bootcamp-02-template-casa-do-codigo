package br.com.zup.renatomelo.desafiocasadocodigo.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.categoria.Categoria;
import br.com.zup.renatomelo.desafiocasadocodigo.categoria.CategoriaRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.categoria.NovaCategoriaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
        Categoria novaCategoria = novaCategoriaRequest.toCategoria();

        categoriaRepository.save(novaCategoria);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
