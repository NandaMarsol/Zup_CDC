package br.com.zup.casadocodigoapi.controllers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Estado;
import br.com.zup.casadocodigoapi.request.NovoEstadoRequest;

@RestController
public class EstadoController {

	@Autowired
	private EntityManager manager;

	// criando estados
	@PostMapping(value = "/api/estados")
	@Transactional
	public String criarEstado(@RequestBody @Valid NovoEstadoRequest request) {
		Estado novoEstado = request.toModel(manager);
		manager.persist(novoEstado);

		return novoEstado.toString();
	}

}
