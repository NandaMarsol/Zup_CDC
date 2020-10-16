package br.com.zup.casadocodigoapi.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import br.com.zup.casadocodigoapi.model.Estado;
import br.com.zup.casadocodigoapi.model.Pais;
import br.com.zup.casadocodigoapi.validations.ExistsId;

public class DadosNovaCompraRequest {

	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@NotBlank
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;

	@ExistsId(domainClass = Pais.class, fieldName = "id")
	@NotNull
	private Long PaisId;

	@ExistsId(domainClass = Estado.class, fieldName = "id")
	private Long EstadoId;

	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	
	@Deprecated
	public DadosNovaCompraRequest(){
		
	}

	public DadosNovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long paisId, Long estadoId, @NotBlank String telefone,
			@NotBlank String cep) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		PaisId = paisId;
		EstadoId = estadoId;
		this.telefone = telefone;
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "DadosNovaCompraRequest [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", PaisId=" + PaisId + ", EstadoId=" + EstadoId + ", telefone=" + telefone + ", cep=" + cep + "]";
	}

	public boolean documentoValido() {
			Assert.hasLength(documento, "O documento precisa ser preenchido para ser validado!");
			
			CPFValidator cpfValidator = new CPFValidator();
			cpfValidator.initialize(null);
			
			CNPJValidator cnpjValidator = new CNPJValidator();
			cnpjValidator.initialize(null);

		return cpfValidator.isValid(documento, null)
				|| cnpjValidator.isValid(documento, null);
	}

	
	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public String getDocumento() {
		return documento;
	}

	public Long getPaisId() {
		return PaisId;
	}

	public Long getEstadoId() {
		return EstadoId;
	}
	
}
