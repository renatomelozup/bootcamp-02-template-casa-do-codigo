package br.com.zup.renatomelo.desafiocasadocodigo.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByEmail(String email);

    Optional<Autor> findByNomeIgnoreCase(String autor);
}
