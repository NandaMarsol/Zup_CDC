package br.com.zup.casadocodigoapi.validations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigoapi.model.Autor;
import br.com.zup.casadocodigoapi.repository.AutorRepository;
import br.com.zup.casadocodigoapi.request.NovoAutorRequest;

@Component
public class AutorEmailDuplicadoValidator implements Validator {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovoAutorRequest.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors){
		if (errors.hasErrors()){
			return;
		}
		
		NovoAutorRequest request = (NovoAutorRequest) target;
		
		Optional<Autor> possivelAutor = autorRepository
				.findByEmail(request.getEmail());
		
		if (possivelAutor.isPresent()) {
			errors.rejectValue("email", null,
					"Já existe um(a) outro(a) autor(a) com esse mesmo email cadastrado no sistema: "
					+ request.getEmail());
		}
	}
}
