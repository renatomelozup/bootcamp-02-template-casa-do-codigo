package br.com.zup.renatomelo.desafiocasadocodigo.categoria.repository;

import br.com.zup.renatomelo.desafiocasadocodigo.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
