package br.com.zup.casadocodigoapi.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Livro;
import br.com.zup.casadocodigoapi.request.NovoLivroRequest;

@RestController
public class LivroController {

	@PersistenceContext
	private EntityManager manager;

	// criando livro
	@PostMapping(value = "/api/livro")
	@Transactional
	public String criarLivro(@Valid @RequestBody NovoLivroRequest request) {
		Livro livro = request.toModel(manager);
		manager.persist(livro);
		return livro.toString();

	}

}
