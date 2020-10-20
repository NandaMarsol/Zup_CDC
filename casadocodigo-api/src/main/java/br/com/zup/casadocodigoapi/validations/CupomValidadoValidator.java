package br.com.zup.casadocodigoapi.validations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigoapi.model.Cupom;
import br.com.zup.casadocodigoapi.repository.CupomRepository;
import br.com.zup.casadocodigoapi.request.DadosNovaCompraRequest;

@Component
public class CupomValidadoValidator implements Validator {

	@Autowired
	private CupomRepository cupomRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return DadosNovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		DadosNovaCompraRequest request = (DadosNovaCompraRequest) target;
		Optional<String> possivelCodigo = request.getCodigoCupom();
		if(possivelCodigo.isPresent()) {
			Cupom cupom = cupomRepository.getByCodigo(possivelCodigo.get());
			if(!cupom.valido()) {
				errors.rejectValue("codigoCupom", null, "Esse cupom expirou e não é mais válido");
			}
		}
	}

}
