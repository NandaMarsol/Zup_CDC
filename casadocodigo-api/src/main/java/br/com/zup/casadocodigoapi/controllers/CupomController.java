package br.com.zup.casadocodigoapi.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Cupom;

@RestController
public class CupomController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/api/cupons")
	@Transactional
	public String criarCupom(@RequestBody @Valid CupomRequest request) {
		
		Cupom novoCupom = request.toModel();
		manager.persist(novoCupom);
		
		return novoCupom.toString();
	}

}
