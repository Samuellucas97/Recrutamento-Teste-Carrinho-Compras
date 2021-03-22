package br.com.improving.carrinho;

import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item {

	private final Produto produto;
	private BigDecimal valorUnitario;
	private int quantidade;

	/**
	 * Construtor da classe Item.
	 *
	 * @param produto	Produto do item
	 * @param valorUnitario Valor unitário do produto
	 * @param quantidade	Quantidade de produtos
	 */
	public Item(Produto produto, BigDecimal valorUnitario, int quantidade) {
		this.produto = produto;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
	}

	/**
	 * Retorna o produto.
	 *
	 * @return Produto
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * Retorna o valor unitário do item.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/**
	 * Retorna a quantidade dos item.
	 *
	 * @return int
	 */
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	/**
	 * Retorna o valor total do item.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		final BigDecimal valorTotal = valorUnitario.multiply(BigDecimal.valueOf(quantidade));
		return  valorTotal;
	}
}