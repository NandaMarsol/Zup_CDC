package br.com.zup.casadocodigoapi.validations;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigoapi.model.Categoria;
import br.com.zup.casadocodigoapi.repository.CategoriaRepository;
import br.com.zup.casadocodigoapi.request.NovaCategoriaRequest;

public class CategoriaNomeDuplicadoValidator implements Validator {

	private CategoriaRepository categoriaRepository;

	public CategoriaNomeDuplicadoValidator(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCategoriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovaCategoriaRequest request = (NovaCategoriaRequest) target;
		Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(request.getNome());
		
		if(possivelCategoria.isPresent()){ // se a possivelCategoria existe, geramos um erro de validação, pois categoria deve ser única no BD
			errors.rejectValue("nome", null, "Já existe uma categoria cadastrada com esse nome!"); // rejeitando o nome do campo
		}
	}

}
