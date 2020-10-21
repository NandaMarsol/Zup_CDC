package br.com.zup.casadocodigoapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class CupomAplicado {
	
	@ManyToOne
	private Cupom cupom;
	
	@NotNull @Positive
	private BigDecimal percentualDescontoMomento;
	
	@NotNull @Future
	private LocalDate validadeMomento;
	
	@Deprecated
	public CupomAplicado(){
	}

	public CupomAplicado(Cupom cupom) {
		super();
		this.cupom = cupom;
		this.percentualDescontoMomento = cupom.getPercentualDesconto();
		this.validadeMomento = cupom.getValidade();
	}

	@Override
	public String toString() {
		return "CupomAplicado [cupom=" + cupom + ", percentualDescontoMomento=" + percentualDescontoMomento
				+ ", validadeMomento=" + validadeMomento + "]";
	}

	public BigDecimal getPercentualDescontoMomento() {
		return percentualDescontoMomento;
	}
	
}
