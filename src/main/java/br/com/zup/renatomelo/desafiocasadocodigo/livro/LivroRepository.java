package br.com.zup.renatomelo.desafiocasadocodigo.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<LivroIdTituloProjection> findBy();
    Optional<LivroPaginaDetalheProjection> getById(Long id);
}
