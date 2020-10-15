package br.com.zup.casadocodigoapi.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Livro;
import br.com.zup.casadocodigoapi.response.DetalheLivroResponse;

@RestController
public class DetalheLivroController {

	@PersistenceContext
	private EntityManager manager;

	// método para buscar detalhes de um livro pelo seu ID
	@GetMapping(value = "/api/detalhes/{id}")
	public ResponseEntity<DetalheLivroResponse> detalhesLivros(@PathVariable("id") Long id) {
		Livro livroBuscado = manager.find(Livro.class, id);
		if (livroBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		DetalheLivroResponse detalheLivroResponse = new DetalheLivroResponse(livroBuscado);
		return ResponseEntity.ok(detalheLivroResponse);
	}

}
