package br.com.zup.renatomelo.desafiocasadocodigo.livro.controller;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.model.Autor;
import br.com.zup.renatomelo.desafiocasadocodigo.autor.repository.AutorRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.categoria.model.Categoria;
import br.com.zup.renatomelo.desafiocasadocodigo.categoria.repository.CategoriaRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.model.Livro;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.response.LivroPaginaDetalheProjection;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.repository.LivroRepository;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.request.NovoLivroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        Optional<Autor> autor = autorRepository.findById(novoLivroRequest.getAutorId());
        Optional<Categoria> categoria = categoriaRepository.findById(novoLivroRequest.getCategoriaId());

        Livro novoLivro = novoLivroRequest.paraLivro(autor.get(), categoria.get());

        livroRepository.save(novoLivro);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listaLivros() {

        return new ResponseEntity<>(livroRepository.findBy(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> detalheLivroId(@PathVariable Long id) {
        Optional<LivroPaginaDetalheProjection> livro = livroRepository.getById(id);
        if(livro.isPresent()){
            return new ResponseEntity<>(livro.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
