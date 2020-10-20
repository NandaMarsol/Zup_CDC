package br.com.zup.casadocodigoapi.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zup.casadocodigoapi.model.Cupom;
import br.com.zup.casadocodigoapi.validations.UniqueValue;

public class CupomRequest {
	
	@UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
	@NotBlank private String codigo;

	@NotNull @Positive
	private BigDecimal percentualDesconto;
	
	@NotNull @Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate validade;

	@Deprecated
	public CupomRequest(){
		
	}
	
	public CupomRequest(@NotBlank String codigo, @NotNull @Positive BigDecimal percentualDesconto) {
		super();
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public String getCodigo() {
		return codigo;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	
	public Cupom toModel() {
		return new Cupom(codigo, percentualDesconto, validade);
	}
	
	
}
