package br.com.improving.carrinho;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import br.com.improving.carrinho.exception.ProdutoInvalidoException;
import br.com.improving.carrinho.exception.QuantidadeNegativaException;
import br.com.improving.carrinho.exception.ValorUnitarioNegativoException;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

	private List<Item> itens;

	public CarrinhoCompras() {
		this.itens = new ArrayList<>();
	}

	/**
	 * Permite a adição de um novo item no carrinho de compras.
	 *
	 * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
	 * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
	 * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
	 * o passado como parâmetro.
	 *
	 * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
	 *
	 * @param produto Novo produto
	 * @param valorUnitario Valor unitário do item
	 * @param quantidade Quantidade de itens
	 */
	public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
		if (produto == null) throw new ProdutoInvalidoException("Produto não pode ser nulo");
		else if (valorUnitario.compareTo(BigDecimal.ZERO) < 0)
			throw new ValorUnitarioNegativoException("O valor unitário do item deve ser maior ou igual a zero");
		else if (quantidade < 0)
			throw new QuantidadeNegativaException("O valor unitário do item deve ser maior ou igual a zero");
		else {

			Item resultadoItem = this.itens.stream()
					.filter(item -> item.getProduto().equals(produto))
					.findFirst()
					.orElse(null);

			if (resultadoItem == null) {
				final Item novoItem = new Item(produto, valorUnitario, quantidade);
				this.itens.add(novoItem);
			} else {
				final int posicaoItem = this.itens.indexOf(resultadoItem);

				resultadoItem.setQuantidade(resultadoItem.getQuantidade() + quantidade);

				if ( resultadoItem.getValorUnitario().compareTo(valorUnitario) != 0 )
					resultadoItem.setValorUnitario(valorUnitario);

				this.itens.set(posicaoItem, resultadoItem);
			}
		}
	}

	/**
	 * Permite a remoção do item que representa este produto do carrinho de compras.
	 *
	 * @param produto	Produto que se deseja ser removido
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
	 * caso o produto não exista no carrinho.
	 */
	public boolean removerItem(Produto produto) {
		if (produto == null) return false;

		final Optional<Item> itemEspecifico = encontrarItem(produto);

		if (!itemEspecifico.isPresent()) return false;

		this.itens.remove(itemEspecifico.get());
		return true;
	}

	/**
	 * Permite a remoção do item de acordo com a posição.
	 * Essa posição deve ser determinada pela ordem de inclusão do produto na
	 * coleção, em que zero representa o primeiro item.
	 *
	 * @param posicaoItem Posição do item na lista de compras (Carrinho de compras)
	 * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
	 * caso o produto não exista no carrinho.
	 */
	public boolean removerItem(int posicaoItem) {
		final int quantidadeDeItens = itens.size();

		if (posicaoItem < 0 || posicaoItem > quantidadeDeItens-1) return false;

		this.itens.remove(posicaoItem);
		return true;
	}

	/**
	 * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
	 * de todos os itens que compõem o carrinho.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTotal() {
		return this.itens.stream().map(e -> e.getValorTotal())
				.reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}

	/**
	 * Retorna a lista de itens do carrinho de compras.
	 *
	 * @return itens
	 */
	public Collection<Item> getItens() {
		return this.itens;
	}

	private Optional<Item> encontrarItem(Produto produto) {
		return this.itens.stream()
				.filter(item -> item.getProduto().equals(produto))
				.findFirst();
	}
}