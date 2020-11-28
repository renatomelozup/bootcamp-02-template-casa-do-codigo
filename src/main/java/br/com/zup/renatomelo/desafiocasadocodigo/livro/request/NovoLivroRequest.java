package br.com.zup.renatomelo.desafiocasadocodigo.livro.request;

import br.com.zup.renatomelo.desafiocasadocodigo.autor.model.Autor;
import br.com.zup.renatomelo.desafiocasadocodigo.categoria.model.Categoria;
import br.com.zup.renatomelo.desafiocasadocodigo.livro.model.Livro;
import br.com.zup.renatomelo.desafiocasadocodigo.validator.HasRecord;
import br.com.zup.renatomelo.desafiocasadocodigo.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    @Size(max = 4000)
    private String sumario;

    @NotNull
    @DecimalMin(value = "20.0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Long paginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDisponivel;

    @NotNull
    @HasRecord(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    @NotNull
    @HasRecord(domainClass = Autor.class, fieldName = "id")
    private Long autorId;

    public String getSumario() {
        return sumario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Long getPaginas() {
        return paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataDisponivel() {
        return dataDisponivel;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    @Deprecated
    public NovoLivroRequest() {
    }

    /**
     * @param titulo = é obrigatório e deve ser único
     * @param resumo = é obrigatório e deve ter no máximo 500 caracteres
     * @param preco = é obrigatório e deve ser maior que 20
     * @param paginas = é obrigatório e deve ter mais de 100
     * @param isbn = é obrigatório e único
     * @param dataDisponivel = é obrigatório e deve estar no futuro
     * @param categoriaId = é obrigatório
     * @param autorId = é obrigatório
     */
    public NovoLivroRequest(@NotBlank String titulo,
                            @NotBlank @Size(max = 500) String resumo,
                            @NotNull @DecimalMin(value = "20") @Digits(integer = 8, fraction = 2) BigDecimal preco,
                            @NotNull @Min(100) Long paginas,
                            @NotBlank String isbn,
                            @Future LocalDate dataDisponivel,
                            @NotNull Long categoriaId,
                            @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dataDisponivel = dataDisponivel;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    /**
     * @param titulo = é obrigatório e deve ser único
     * @param resumo = é obrigatório e deve ter no máximo 500 caracteres
     * @param sumario = não é obritatório, tem tamnho livre e deve estar no formato markdown
     * @param preco = é obrigatório e deve ser maior que 20
     * @param paginas = é obrigatório e deve ter mais de 100
     * @param isbn = é obrigatório e único
     * @param dataDisponivel = é obrigatório e deve estar no futuro
     * @param categoriaId = é obrigatório
     * @param autorId = é obrigatório
     */
    public NovoLivroRequest(@NotBlank String titulo,
                            @NotBlank @Size(max = 500) String resumo,
                            @Size(max = 4000) String sumario,
                            @NotNull @DecimalMin(value = "20") @Digits(integer = 8, fraction = 2) BigDecimal preco,
                            @NotNull @Min(100) Long paginas,
                            @NotBlank String isbn,
                            @Future LocalDate dataDisponivel,
                            @NotNull Long categoriaId,
                            @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
        this.dataDisponivel = dataDisponivel;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro paraLivro(Autor autor, Categoria categoria) {
        return new Livro(this.titulo,
                this.resumo,
                this.sumario,
                this.preco,
                this.paginas,
                this.isbn,
                this.dataDisponivel,
                autor,
                categoria);
    }
}
