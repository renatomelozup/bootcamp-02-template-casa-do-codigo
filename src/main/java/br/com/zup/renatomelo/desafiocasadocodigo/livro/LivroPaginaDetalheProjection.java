package br.com.zup.renatomelo.desafiocasadocodigo.livro;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.AutorNomeDescricaoProjection;

public interface LivroPaginaDetalheProjection {
    String getTitulo();
    String getResumo();
    String getSumario();
    String getPreco();
    String getPaginas();
    String getIsbn();
    AutorNomeDescricaoProjection getAutor();
}
