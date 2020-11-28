package br.com.zup.renatomelo.desafiocasadocodigo.livro.repository;

import br.com.zup.renatomelo.desafiocasadocodigo.livro.model.Livro;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.response.LivroIdTituloProjection;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.response.LivroPaginaDetalheProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<LivroIdTituloProjection> findBy();
    Optional<LivroPaginaDetalheProjection> getById(Long id);
}
