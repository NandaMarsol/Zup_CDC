package br.com.zup.casadocodigoapi.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Compra;
import br.com.zup.casadocodigoapi.response.DetalhesCompraResponse;

@RestController
public class DetalhesCompraController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/api/compras/{id}")
	public ResponseEntity<DetalhesCompraResponse> detalhesDaCompra(@PathVariable Long id){
		Compra compra = manager.find(Compra.class, id);
		DetalhesCompraResponse response = new DetalhesCompraResponse(compra);
		return ResponseEntity.ok(response);
	}

}
