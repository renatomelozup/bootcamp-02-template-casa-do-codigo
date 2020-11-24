package br.com.zup.renatomelo.desafiocasadocodigo.livro;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.Autor;
import br.com.zup.renatomelo.desafiocasadocodigo.categoria.Categoria;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    @Size(max = 4000)
    private String sumario;

    @NotNull
    @DecimalMin(value = "20")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Long paginas;

    @NotBlank
    private String isbn;

    @Future
    private LocalDate dataDisponivel;

    @NotNull
    @OneToOne
    private Autor autor;

    @NotNull
    @OneToOne
    private Categoria categoria;

    @Deprecated
    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Livro(@NotBlank String titulo,
                 @NotBlank @Size(max = 500) String resumo,
                 @Size(max = 4000) String sumario,
                 @NotNull @DecimalMin(value = "20") @Digits(integer = 8, fraction = 2) BigDecimal preco,
                 @NotNull @Min(100) Long paginas,
                 @NotBlank String isbn,
                 @Future LocalDate dataDisponivel,
                 @NotNull Autor autor,
                 @NotNull Categoria categoria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dataDisponivel = dataDisponivel;
        this.autor = autor;
        this.categoria = categoria;
    }
}
