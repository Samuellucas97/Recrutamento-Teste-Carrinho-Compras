package br.com.improving.carrinho;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import br.com.improving.carrinho.exception.ProdutoInvalidoException;
import br.com.improving.carrinho.exception.QuantidadeNegativaException;
import br.com.improving.carrinho.exception.ValorUnitarioNegativoException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarrinhoComprasTest {
	private CarrinhoCompras carrinhoCompras = new CarrinhoCompras();

	@Test
	void adicionarItem_CriaNovoItemNoCarrinho_QuandoSucesso() {
		final Produto produto =  new Produto(10L, "fake");
		final BigDecimal valorUnitario = BigDecimal.valueOf(1);
		final int quantidade = 10;

		assertThatCode(() -> carrinhoCompras.adicionarItem(produto, valorUnitario, quantidade))
				.doesNotThrowAnyException();
	}

	@Test
	void adicionarItem_LancaExcecao_QuandoProdutoNulo() {
		final Produto produto = null;
		final BigDecimal valorUnitario = BigDecimal.valueOf(1);
		final int quantidade = 10;

		assertThatExceptionOfType(ProdutoInvalidoException.class)
				.isThrownBy(() -> carrinhoCompras.adicionarItem(produto, valorUnitario, quantidade));
	}

	@Test
	void adicionarItem_LancaExcecao_QuandoQuantidadeNegativa() {
		final Produto produto = new Produto(10L, "fake");
		final BigDecimal valorUnitario = BigDecimal.valueOf(1);
		final int quantidade = -10;

	assertThatExceptionOfType(QuantidadeNegativaException.class)
				.isThrownBy(() -> carrinhoCompras.adicionarItem(produto, valorUnitario, quantidade));
	}

	@Test
	void adicionarItem_LancaExcecao_QuandoValorUnitarioNegativo() {
		final Produto produto = new Produto(10L, "fake");
		final BigDecimal valorUnitario = BigDecimal.valueOf(-1);
		final int quantidade = 10;

		assertThatExceptionOfType(ValorUnitarioNegativoException.class)
				.isThrownBy(() -> carrinhoCompras.adicionarItem(produto, valorUnitario, quantidade));
	}

	@Test
	void getItens_RetornaZeroElementos_QuandoNaoHaItens() {
		assertThat(carrinhoCompras.getItens().size()).isEqualTo(0);
	}

	@Test
	void getItens_RetornaElementos_QuandoHaItens() {
		final Produto produto = new Produto(10L, "fake");
		final BigDecimal valorUnitario = BigDecimal.valueOf(1);
		final int quantidade = 10;
		carrinhoCompras.adicionarItem(produto, valorUnitario, quantidade);

		assertThat(carrinhoCompras.getItens().size()).isEqualTo(1);
	}

	@Test
	void removeItem_RetornaVerdadeiro_QuandoSucesso() {
		final Produto produto = new Produto(10L, "fake");
		final BigDecimal valorUnitario = BigDecimal.valueOf(1);
		final int quantidade = 10;
		carrinhoCompras.adicionarItem(produto, valorUnitario, quantidade);

		assertThat(carrinhoCompras.removerItem(produto)).isEqualTo(true);
	}

	@Test
	void removerItem_RetornaFalso_QuandoProdutoNulo() {
		Produto produto = null;
		assertThat(carrinhoCompras.removerItem(produto)).isEqualTo(false);
	}

	@Test
	void removerItem_RetornaFalso_QuandoProdutoNaoEstaPresente() {
		Produto produto = new Produto(10L, "fake");;
		assertThat(carrinhoCompras.removerItem(produto)).isEqualTo(false);
	}


	@Test
	void removeItem_RetornaTrue_QuandoSucesso() {
		final Produto produto = new Produto(10L, "fake");
		final BigDecimal valorUnitario = BigDecimal.valueOf(1);
		final int quantidade = 10;
		carrinhoCompras.adicionarItem(produto, valorUnitario, quantidade);

		assertThat(carrinhoCompras.removerItem(0)).isEqualTo(true);
	}

	@Test
	void removeItem_RetornaFalse_QuandoPosicaoItemNegativa() {
		assertThat(carrinhoCompras.removerItem(-1)).isEqualTo(false);
	}

	@Test
	void removeItem_RetornaFalse_QuandoPosicaoItemMaiorQueQuantidadeDeItens() {
		assertThat(carrinhoCompras.removerItem(1)).isEqualTo(false);
	}

	@Test
	void getValorTotal_RetornaZero_QuandoNaoHaItemNoCarrinho() {
		assertThat(carrinhoCompras.getValorTotal()).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	void getValorTotal_RetornaSomatorioDosValorDosItens_QuandoHaItensNoCarrinho() {
		final Produto produto1 = new Produto(10L, "fake");
		final BigDecimal valorUnitario1 = BigDecimal.valueOf(1);
		final int quantidade1 = 10;
		carrinhoCompras.adicionarItem(produto1, valorUnitario1, quantidade1);

		final Produto produto2 = new Produto(11L, "new fake");
		final BigDecimal valorUnitario2 = BigDecimal.valueOf(1);
		final int quantidade2 = 10;
		carrinhoCompras.adicionarItem(produto2, valorUnitario2, quantidade2);

		final BigDecimal valorTotalEsperado = BigDecimal.valueOf(20);
		assertThat(carrinhoCompras.getValorTotal()).isEqualTo(valorTotalEsperado);
	}
}