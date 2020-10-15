package br.com.zup.casadocodigoapi.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zup.casadocodigoapi.model.Autor;
import br.com.zup.casadocodigoapi.model.Categoria;

import br.com.zup.casadocodigoapi.model.Livro;
import br.com.zup.casadocodigoapi.validations.ExistsId;
import br.com.zup.casadocodigoapi.validations.UniqueValue;


public class NovoLivroRequest {
	
	@NotBlank 
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo") private String titulo;
	
	@NotBlank @Size(max = 500) private String resumo;
	@NotBlank private String sumario; 
	@NotNull @Min(20) private BigDecimal preco;
	@NotNull @Min(100) private int numeroDePaginas;
	
	@NotBlank 
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn") private String isbn;
	
	@NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataDePublicacao;
	
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	@NotNull private Long categoriaId;
	
	@ExistsId(domainClass = Autor.class, fieldName = "id")
	@NotNull private Long autorId;
	
	@Deprecated
	public NovoLivroRequest() { // o código só está compilando incluindo esse construtor
	}
	
	public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) int numeroDePaginas, @NotBlank String isbn,
			@NotNull @Future LocalDate dataDePublicacao, @NotNull Long categoriaId, @NotNull Long autorId) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroDePaginas = numeroDePaginas;
		this.isbn = isbn;
		this.dataDePublicacao = dataDePublicacao;
		this.categoriaId = categoriaId;
		this.autorId = autorId;
	}

	// getters
	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataDePublicacao() {
		return dataDePublicacao;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public Long getAutorId() {
		return autorId;
	}
	
	public Livro toModel(EntityManager manager){
        Autor autor = manager.find(Autor.class, autorId);
        Categoria categoria = manager.find(Categoria.class, categoriaId);

        Assert.notNull(autor, "O autor não existe no banco de dados...");
        Assert.notNull(categoria, "A categoria não existe no banco de dados...");
        
        return new Livro(titulo, resumo, sumario, preco, numeroDePaginas, isbn, dataDePublicacao, categoria, autor);

    }

}

// até aqui tudo compilando e passando nos testes de validações do Swagger