package br.com.improving.carrinho;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarrinhoComprasFactoryTest {
	private CarrinhoComprasFactory carrinhoComprasFactory = new CarrinhoComprasFactory();
	private Map<String, CarrinhoCompras> carrinhos = new HashMap<>();

	@Test
	void criar_RetornaNovoCarrinhoDeCompras_QuandoAdicionadoPelaPrimeiraVez() {
		final String identificacaoCliente = "fake";
		final CarrinhoCompras carrinhoComprasResultado = carrinhoComprasFactory.criar(identificacaoCliente);
		assertThat(carrinhoComprasResultado).isNotNull();
	}

	@Test
	void getValorTicketMedio_RetornaZero_QuandoNaoHaCarrinhoDeCompra() {
		assertThat(carrinhoComprasFactory.getValorTicketMedio()).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	void invalidar_RetornaTrue_QuandoCarrinhoDeComprasCorrespondente() {
		final String identificacaoCliente = "fake";
		final CarrinhoCompras carrinhoComprasResultado = carrinhoComprasFactory
				.criar(identificacaoCliente);

		assertThat(carrinhoComprasFactory.invalidar(identificacaoCliente))
				.isEqualTo(true);
	}

	@Test
	void invalidar_RetornaFalso_QuandoNaoHaCarrinhoDeComprasCorrespondente() {
		final String identificacaoCliente = "fake";
		assertThat(carrinhoComprasFactory.invalidar(identificacaoCliente))
				.isEqualTo(false);
	}
}