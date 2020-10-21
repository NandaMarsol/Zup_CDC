package br.com.zup.casadocodigoapi.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@NotNull @Valid
	private Compra compra;
	
	@Embedded
	@ElementCollection
	@Size(min = 1) private Set<ItemPedido> itens = new HashSet<>();
	
	@Deprecated
	public Pedido() {
	}
	
	public Pedido(@NotNull @Valid Compra compra, @Size(min = 1) Set<ItemPedido> itens) {
		Assert.isTrue(!itens.isEmpty(), " o pedido deve ter pelo menos 1 item adicionado");
		this.compra = compra;
		this.itens.addAll(itens);
	}
	

	public BigDecimal calcularTotal() {
		return itens.stream().map(ItemPedido -> ItemPedido.total()).reduce(new BigDecimal(0), BigDecimal::add);
	}

	@Override
	public String toString() {
		return "Pedido [itens=" + itens + "]";
	}

	public Long getId() {
		return id;
	}

	public Compra getCompra() {
		return compra;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

}
