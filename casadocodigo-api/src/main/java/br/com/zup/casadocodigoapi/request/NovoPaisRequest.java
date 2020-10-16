package br.com.zup.casadocodigoapi.request;

import javax.validation.constraints.NotBlank;

import br.com.zup.casadocodigoapi.model.Pais;
import br.com.zup.casadocodigoapi.validations.UniqueValue;

public class NovoPaisRequest {
	
	@UniqueValue(domainClass = Pais.class, fieldName = "nome") // usando anotação para validar nome único do país
	@NotBlank private String nome;

	@Deprecated
	public NovoPaisRequest(){
		
	}
	
	// getters and setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
