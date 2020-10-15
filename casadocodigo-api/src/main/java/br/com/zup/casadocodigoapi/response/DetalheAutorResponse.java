package br.com.zup.casadocodigoapi.response;

import br.com.zup.casadocodigoapi.model.Autor;

public class DetalheAutorResponse {
	
	private String nome;
	private String descricao;
	
	public DetalheAutorResponse(Autor autor) {
		nome = autor.getNome();
		descricao = autor.getDescricao();
		
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
