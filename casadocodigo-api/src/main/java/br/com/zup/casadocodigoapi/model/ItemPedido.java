package br.com.zup.casadocodigoapi.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class ItemPedido {
	
	@ManyToOne
	@NotNull private Livro livro;
	
	@Positive private int quantidade;
	@Positive private BigDecimal precoMomento;
	
	@Deprecated
	public ItemPedido() {
	}

	public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoMomento = livro.getPreco();
	}

	@Override
	public String toString() {
		return "ItemPedido [livro=" + livro + ", quantidade=" + quantidade + ", precoMomento=" + precoMomento + "]";
	}
	
	public BigDecimal total() {
		return precoMomento.multiply(new BigDecimal(quantidade));
	}
	
	

}
