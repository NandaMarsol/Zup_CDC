package br.com.zup.casadocodigoapi.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.casadocodigoapi.model.ItemPedido;
import br.com.zup.casadocodigoapi.model.Livro;
import br.com.zup.casadocodigoapi.validations.ExistsId;

public class NovoPedidoItemRequest {

	@ExistsId(domainClass = Livro.class, fieldName = "id")
	@NotNull
	private Long livroId;

	@Positive
	private int quantidade;
	
	@Deprecated
	public NovoPedidoItemRequest() {
		
	}

	public NovoPedidoItemRequest(@NotNull Long livroId, @Positive int quantidade) {
		super();
		this.livroId = livroId;
		this.quantidade = quantidade;
	}

	public Long getLivroId() {
		return livroId;
	}

	public int getQuantidade() {
		return quantidade;
	}

	@Override
	public String toString() {
		return "NovoPedidoItemRequest [livroId=" + livroId + ", quantidade=" + quantidade + "]";
	}

	public ItemPedido toModel(EntityManager manager) {
		@NotNull Livro livro = manager.find(Livro.class, livroId);
		return new ItemPedido(livro, quantidade);
	}

}


