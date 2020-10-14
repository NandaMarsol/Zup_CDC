package br.com.zup.casadocodigoapi.request;

import javax.validation.constraints.NotBlank;

import br.com.zup.casadocodigoapi.model.Categoria;
import br.com.zup.casadocodigoapi.validations.UniqueValue;

public class NovaCategoriaRequest {
	
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome") // adicionando anotação UniqueValue criada
	@NotBlank private String nome;

	public String getNome() {
		return nome;
	}
}
