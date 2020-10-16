package br.com.zup.casadocodigoapi.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Pais;
import br.com.zup.casadocodigoapi.request.NovoPaisRequest;

@RestController
public class PaisController {
	
	@PersistenceContext
	private EntityManager manager;
	
	// criando países
	@PostMapping(value = "/api/paises")
	@Transactional
	public String criarPais(@RequestBody @Valid NovoPaisRequest request){
		Pais novoPais = new Pais(request.getNome());
		manager.persist(novoPais);
		
		return novoPais.toString();
		
	}

}
