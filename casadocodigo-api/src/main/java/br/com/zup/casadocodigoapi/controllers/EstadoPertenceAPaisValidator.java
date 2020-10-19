package br.com.zup.casadocodigoapi.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigoapi.model.Estado;
import br.com.zup.casadocodigoapi.model.Pais;
import br.com.zup.casadocodigoapi.request.DadosNovaCompraRequest;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return DadosNovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		DadosNovaCompraRequest request = (DadosNovaCompraRequest) target;

		if (request.temEstado()) {
			Pais pais = manager.find(Pais.class, request.getPaisId());
			Estado estado = manager.find(Estado.class, request.getEstadoId());
			if (!estado.pertenceAPais(pais)) {
				errors.rejectValue("estadoId", null, " esse Estado não corresponde ao País selecionado.");
			}

		}
	}
}
