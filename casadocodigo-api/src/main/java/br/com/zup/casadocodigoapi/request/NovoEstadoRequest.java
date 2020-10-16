package br.com.zup.casadocodigoapi.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.casadocodigoapi.model.Estado;
import br.com.zup.casadocodigoapi.model.Pais;
import br.com.zup.casadocodigoapi.validations.ExistsId;
import br.com.zup.casadocodigoapi.validations.UniqueValue;

public class NovoEstadoRequest {

	@UniqueValue(domainClass = Estado.class, fieldName = "nome") // usando anotação para validar nome único do Estado
	@NotBlank
	private String nome;

	@ExistsId(domainClass = Pais.class, fieldName = "id") // anotação para validar que o id existe no bd
	@NotNull
	private Long paisId;
	
	@Deprecated
	public NovoEstadoRequest() {
		
	}

	public NovoEstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
		super();
		this.nome = nome;
		this.paisId = paisId;
	}

	@Override
	public String toString() {
		return "NovoEstadoRequest [nome=" + nome + ", paisId=" + paisId + "]";
	}

	public Estado toModel(EntityManager manager) {
		return new Estado(nome, manager.find(Pais.class, paisId));
	}

	public String getNome() {
		return nome;
	}

	public Long getPaisId() {
		return paisId;
	}
	

}
