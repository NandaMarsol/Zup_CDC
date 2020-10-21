package br.com.zup.casadocodigoapi.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Cupom;
import br.com.zup.casadocodigoapi.repository.CupomRepository;

@RestController
public class CupomController {
	
	@Autowired
	private CupomRepository cupomRepository;
	 
	
	@PostMapping(value = "/api/cupons")
	@Transactional
	public String criarCupom(@RequestBody @Valid CupomRequest request) {
		
		Cupom novoCupom = request.toModel();
		cupomRepository.save(novoCupom);
		return novoCupom.toString();
	}
	
	@PutMapping(value = "/api/cupons/{id}")
	@Transactional
	public String alterarCupom(@PathVariable Long id, @Valid @RequestBody CupomRequest request) {
		Cupom novoCupom = request.toModel();
		Cupom cupomAlterado = cupomRepository.getOne(id);
		cupomAlterado.setCodigo(novoCupom.getCodigo());
		cupomAlterado.setPercentualDesconto(novoCupom.getPercentualDesconto());
		cupomAlterado.setValidade(novoCupom.getValidade());
		cupomRepository.save(cupomAlterado);
		return "Cupom id " + id + " foi alterado";
	}

}
