package br.com.zup.casadocodigoapi.request;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import br.com.zup.casadocodigoapi.model.Compra;
import br.com.zup.casadocodigoapi.model.Cupom;
import br.com.zup.casadocodigoapi.model.Estado;
import br.com.zup.casadocodigoapi.model.Pais;
import br.com.zup.casadocodigoapi.model.Pedido;
import br.com.zup.casadocodigoapi.repository.CupomRepository;
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
	private Long paisId;

	@ExistsId(domainClass = Estado.class, fieldName = "id")
	private Long estadoId;

	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	
	@NotNull @Valid
	private NovoPedidoRequest pedido;
	
	@ExistsId(domainClass = Cupom.class, fieldName = "codigo")
	private String codigoCupom;
	
	@Deprecated
	public DadosNovaCompraRequest(){
		
	}

	public DadosNovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long paisId, Long estadoId, @NotBlank String telefone,
			@NotBlank String cep, @NotNull @Valid NovoPedidoRequest pedido) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.paisId = paisId;
		this.estadoId = estadoId;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = pedido;
	}


	@Override
	public String toString() {
		return "DadosNovaCompraRequest [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", paisId=" + paisId + ", estadoId=" + estadoId + ", telefone=" + telefone + ", cep=" + cep
				+ ", pedido=" + pedido + "]";
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
		return paisId;
	}

	public Long getEstadoId() {
		return estadoId;
	}
	
	public NovoPedidoRequest getPedido() {
		return pedido;
	}

	public Optional<String> getCodigoCupom() {
		return Optional.ofNullable(codigoCupom);
	}

	public void setCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
	}

	public boolean temEstado() {
		return estadoId != null;
	}

	public Compra toModel(EntityManager manager, CupomRepository cupomRepository) {
		@NotNull Pais pais = manager.find(Pais.class, paisId);
		
		Function<Compra, Pedido>  funcaoCriacaoPedido = pedido.toModel(manager);
		
		Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, 
				pais, telefone, cep, funcaoCriacaoPedido);
		
		if(estadoId != null) {
			compra.setEstado(manager.find(Estado.class, estadoId));
		}
		
		if(StringUtils.hasText(codigoCupom)){
			Cupom cupom = cupomRepository.getByCodigo(codigoCupom);
			compra.aplicaCupom(cupom);
		}
		return compra;
	}
}
	
