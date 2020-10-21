package br.com.zup.casadocodigoapi.response;

import java.math.BigDecimal;
import java.util.Set;

import br.com.zup.casadocodigoapi.model.Compra;
import br.com.zup.casadocodigoapi.model.ItemPedido;

public class DetalhesCompraResponse {
	
	private String email;
	private String nome;
	private String sobrenome;
	private String documento;
	private String endereco;
	private String complemento;
	private String pais;
	private String estado;
	private String telefone;
	private String cep;
	private Set<ItemPedido> itens;
	private BigDecimal total;
	private BigDecimal desconto;
	private BigDecimal totalComDesconto;
	
	
	public DetalhesCompraResponse(Compra compra) {
		super();
		this.email = compra.getEmail();
		this.nome = compra.getNome();
		this.sobrenome = compra.getSobrenome();
		this.documento = compra.getDocumento();
		this.endereco = compra.getEndereco();
		this.complemento = compra.getComplemento();
		this.pais = compra.getPais().getNome();
		this.estado = compra.getEstado().getNome();
		this.telefone = compra.getTelefone();
		this.cep = compra.getCep();
		this.itens = compra.getPedido().getItens();
		this.total = compra.total();
		this.desconto = compra.getCupomAplicado().getPercentualDescontoMomento();
		this.totalComDesconto = compra.calcularTotalComDesconto();
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


	public String getDocumento() {
		return documento;
	}


	public String getEndereco() {
		return endereco;
	}


	public String getComplemento() {
		return complemento;
	}


	public String getPais() {
		return pais;
	}


	public String getEstado() {
		return estado;
	}


	public String getTelefone() {
		return telefone;
	}


	public String getCep() {
		return cep;
	}


	public Set<ItemPedido> getItens() {
		return itens;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public BigDecimal getDesconto() {
		return desconto;
	}


	public BigDecimal getTotalComDesconto() {
		return totalComDesconto;
	}
	

}
