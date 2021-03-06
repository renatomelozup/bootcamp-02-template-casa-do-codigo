package br.com.zup.renatomelo.desafiocasadocodigo.autor.repository;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByEmail(String email);

}
