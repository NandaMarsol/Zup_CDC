package br.com.zup.casadocodigoapi.response;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import br.com.zup.casadocodigoapi.model.Livro;

public class DetalheLivroResponse {

	private DetalheAutorResponse autor;
	private String titulo;
	private String resumo;
	private String sumario;
	private BigDecimal preco;
	private int numeroDePaginas;
	private String isbn;
	private String dataDePublicacao;

	public DetalheLivroResponse(Livro livro) {
		autor = new DetalheAutorResponse(livro.getAutor());
		titulo = livro.getTitulo();
		resumo = livro.getResumo();
		sumario = livro.getSumario();
		preco = livro.getPreco();
		numeroDePaginas = livro.getNumeroDePaginas();
		isbn = livro.getIsbn();
		dataDePublicacao = livro.getDataDePublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
	}

	public DetalheAutorResponse getAutor() {
		return autor;
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

	public String getDataDePublicacao() {
		return dataDePublicacao;
	}

}
