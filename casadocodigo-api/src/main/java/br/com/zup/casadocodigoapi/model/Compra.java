package br.com.zup.casadocodigoapi.model;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

	@ManyToOne
	@NotNull
	private Pais pais;

	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;

	@ManyToOne
	private Estado estado;
	
	// dados da compra
	@OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
	private Pedido pedido;
	
	@Embedded
	private CupomAplicado cupomAplicado;
	
	@Deprecated
	public Compra() {
	}

	public Compra(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, @NotNull Pais pais,
			@NotBlank String telefone, @NotBlank String cep, Function<Compra, Pedido> funcaoCriacaoPedido) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.pais = pais;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = funcaoCriacaoPedido.apply(this);
	}

	@Override
	public String toString() {
		return "Compra [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento=" + documento
				+ ", endereco=" + endereco + ", complemento=" + complemento + ", pais=" + pais + ", telefone="
				+ telefone + ", cep=" + cep + ", estado=" + estado + ", pedido = " + pedido + ", cupomAplicado =" +cupomAplicado+ "]";
	}
	
	public void setEstado(@NotNull @Valid Estado estado) {
		Assert.notNull(pais, "Não é possível associar um Estado enquanto o País for nulo");
		Assert.isTrue(estado.pertenceAPais(pais), "Essse Estado não é do País associado a compra");
		this.estado = estado;
	}
	
	public void aplicaCupom(Cupom cupom) {
		Assert.isTrue(cupom.valido(), " O cupom aplicado extá expirado");
		Assert.isNull(cupomAplicado, "Não é possível trocar o cupom de uma compra");
		this.cupomAplicado = new CupomAplicado(cupom);
	}

}
