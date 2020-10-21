package br.com.zup.casadocodigoapi.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.casadocodigoapi.model.Compra;
import br.com.zup.casadocodigoapi.model.ItemPedido;
import br.com.zup.casadocodigoapi.model.Pedido;

public class NovoPedidoRequest {
	
	@NotNull @Positive private BigDecimal total; 
	
	@Valid @Size(min = 1)
	private List<NovoPedidoItemRequest> itens = new ArrayList<>();
	
	@Deprecated
	public NovoPedidoRequest() {
	}

	public NovoPedidoRequest(@NotNull @Positive BigDecimal total,
			@Valid @Size(min = 1) List<NovoPedidoItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}

	public List<NovoPedidoItemRequest> getItens() {
		return itens;
	}


	@Override
	public String toString() {
		return "NovoPedidoRequest [total=" + total + ", itens=" + itens + "]";
	}

	public Function<Compra, Pedido> toModel(EntityManager manager) {
		Set<ItemPedido> itensCalculados = itens.stream().map(item -> item.toModel(manager)).collect(Collectors.toSet());
		
		return (compra) -> { 
			Pedido pedido = new Pedido(compra, itensCalculados);
			return pedido;
		};
	}
	
	public BigDecimal getTotal() {
		return total;
	}


}
