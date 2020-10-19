package br.com.zup.casadocodigoapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String codigo;
	@NotNull
	@Positive
	private BigDecimal percentualDesconto;
	@NotNull
	@Future
	LocalDate validade;

	public Cupom(@NotBlank String codigo, @NotNull BigDecimal percentualDesconto, @NotNull @Future LocalDate validade) {
		super();
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
		this.validade = validade;
	}

	@Override
	public String toString() {
		return "Cupom [id=" + id + ", codigo=" + codigo + ", percentualDesconto=" + percentualDesconto + ", validade="
				+ validade + "]";
	}

}
