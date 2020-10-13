package br.com.zup.casadocodigoapi;

import br.com.zup.casadocodigoapi.model.Autor;

public class TestaAutor {

	public static void main(String[] args) {
		Autor autor = new Autor("Fernanda", "marsol@gmail.com", "testando o método toString...");
		System.out.println(autor.toString());

	}

}
