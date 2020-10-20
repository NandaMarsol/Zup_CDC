package br.com.zup.casadocodigoapi.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Compra;
import br.com.zup.casadocodigoapi.repository.CupomRepository;
import br.com.zup.casadocodigoapi.request.DadosNovaCompraRequest;
import br.com.zup.casadocodigoapi.validations.CupomValidadoValidator;

@RestController
public class CompraController {
	
	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CupomRepository cupomRepository;
	
	@Autowired
	private CupomValidadoValidator cupomValidoValidator;
	
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new DocumentoCpfCnpjValidator(), estadoPertenceAPaisValidator, cupomValidoValidator );
	}
	
	// criando uma compra
	@PostMapping(value = "/api/compras")
	@Transactional
	public String criarCompras(@RequestBody @Valid DadosNovaCompraRequest request){
		
		Compra novaCompra = request.toModel(manager, cupomRepository);
		manager.persist(novaCompra);
		
		return novaCompra.toString();
	}

}
