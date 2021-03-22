package br.com.improving.carrinho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class CarrinhoComprasFactory {

	private Map<String, CarrinhoCompras> carrinhos;

	public CarrinhoComprasFactory() {
		this.carrinhos = new HashMap<>();
	}

	/**
	 * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
	 *
	 * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
	 *
	 * @param identificacaoCliente	Identificação do carrinho de compras do cliente
	 * @return CarrinhoCompras
	 */
	public CarrinhoCompras criar(String identificacaoCliente) {
		if (this.carrinhos.containsKey(identificacaoCliente) )
			return this.carrinhos.get(identificacaoCliente);

		CarrinhoCompras novoCarrinhoCompras = new CarrinhoCompras();
		this.carrinhos.put(identificacaoCliente, novoCarrinhoCompras);
		return novoCarrinhoCompras;
	}

	/**
	 * Retorna o valor do ticket médio no momento da chamada ao método.
	 * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
	 * pela quantidade de carrinhos de compra.
	 * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
	 * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getValorTicketMedio() {
		final BigDecimal somaValorTotalTodosCarrinhos = this.carrinhos.values().stream()
				.map(e -> e.getValorTotal())
				.reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

		if (somaValorTotalTodosCarrinhos.compareTo(BigDecimal.ZERO) == 0)
			return BigDecimal.ZERO;

		final BigDecimal quantidade = new BigDecimal(this.carrinhos.size());
		final BigDecimal ticketMedio = somaValorTotalTodosCarrinhos.divide(quantidade);
		return ticketMedio.setScale(2, RoundingMode.HALF_DOWN);
	}

	/**
	 * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
	 * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
	 *
	 * @param identificacaoCliente Identificação do carrinho de compras do cliente
	 * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
	 * e false caso o cliente não possua um carrinho.
	 */
	public boolean invalidar(String identificacaoCliente) {
		if (!this.carrinhos.containsKey(identificacaoCliente) )
			return false;

		this.carrinhos.remove(identificacaoCliente);
		return true;
	}
}