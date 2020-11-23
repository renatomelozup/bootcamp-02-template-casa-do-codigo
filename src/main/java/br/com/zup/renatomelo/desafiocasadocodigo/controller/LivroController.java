package br.com.zup.renatomelo.desafiocasadocodigo.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.Autor;
import br.com.zup.renatomelo.desafiocasadocodigo.autor.AutorRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.categoria.Categoria;
import br.com.zup.renatomelo.desafiocasadocodigo.categoria.CategoriaRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.Livro;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.LivroRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.NovoLivroRequest;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> novoLivro(@RequestBody @Valid NovoLivroRequest novoLivroRequest) {

        if(novoLivroRequest.getSumario() == null){
            novoLivroRequest.setSumario("");
        }

        Optional<Autor> autor = autorRepository.findByNomeIgnoreCase(novoLivroRequest.getAutor());
        Optional<Categoria> categoria = categoriaRepository.findByNomeIgnoreCase(novoLivroRequest.getCategoria());

        if(autor.isPresent() && categoria.isPresent()) {

        }
        Livro novoLivro = novoLivroRequest.paraLivro(autor.get(), categoria.get());

        livroRepository.save(novoLivro);

        return new ResponseEntity<>(HttpStatus.OK);
        //return new ResponseEntity<>("blablabla",HttpStatus.BAD_REQUEST);
    }
}
