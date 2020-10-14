package br.com.zup.casadocodigoapi.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Categoria;
import br.com.zup.casadocodigoapi.repository.CategoriaRepository;
import br.com.zup.casadocodigoapi.request.NovaCategoriaRequest;

@RestController
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	@PostMapping(value = "/api/categoria")
	@Transactional
	public void criarCategoria(@Valid @RequestBody NovaCategoriaRequest request) {
		Categoria novaCategoria = new Categoria(request.getNome());
		categoriaRepository.save(novaCategoria);
		
	}

}
