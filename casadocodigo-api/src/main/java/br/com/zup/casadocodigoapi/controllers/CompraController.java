package br.com.zup.casadocodigoapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.request.DadosNovaCompraRequest;

@RestController
public class CompraController {
	
	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new DocumentoCpfCnpjValidator(), estadoPertenceAPaisValidator);
	}
	
	// criando uma compra
	@PostMapping(value = "/api/compras")
	public String criarCompras(@RequestBody @Valid DadosNovaCompraRequest request){
		return request.toString();
	}

}
