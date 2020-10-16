package br.com.zup.casadocodigoapi.controllers;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigoapi.request.DadosNovaCompraRequest;

public class DocumentoCpfCnpjValidator implements Validator {

	
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
		if (!request.documentoValido()) {
			errors.rejectValue("documento", null, " o documento precisa ser um CPF ou CNPJ");
		}

	}


}
