package br.com.zup.casadocodigoapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

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

	@NotBlank
	private String sumario;

	@NotNull
	@Min(20)
	private BigDecimal preco;

	@NotNull
	@Min(100)
	private int numeroDePaginas;

	@NotBlank
	private String isbn;

	@NotNull
	@Future
	private LocalDate dataDePublicacao;

	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@Valid
	@ManyToOne
	private Autor autor;

	// construtor
	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) int numeroDePaginas, @NotBlank String isbn,
			@NotNull @Future LocalDate dataDePublicacao, @NotNull @Valid Categoria categoria,
			@NotNull @Valid Autor autor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroDePaginas = numeroDePaginas;
		this.isbn = isbn;
		this.dataDePublicacao = dataDePublicacao;
		this.categoria = categoria;
		this.autor = autor;
	}

	@Deprecated
	public Livro() {

	}

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
		return this.dataDePublicacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Autor getAutor() {
		return autor;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario + ", preco="
				+ preco + ", numeroDePaginas=" + numeroDePaginas + ", isbn=" + isbn + ", dataDePublicacao="
				+ dataDePublicacao + ", categoria=" + categoria + ", autor=" + autor + "]";
	}

}

/*
 * obs.: colocamos as mesmas anotações nos atributos dessa classe, além da
 * classe NovoLivroRequest porque sabemos que essa classe livro vai virar uma
 * entidade do JPA e será serializada para o banco de dados; quando for
 * serializada queremos garantir de novo que os dados estão respeitando as
 * constraints da aplicação isso aumenta a segurança;
 * 
 */
