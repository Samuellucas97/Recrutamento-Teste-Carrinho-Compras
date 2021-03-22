package br.com.improving.carrinho;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ItemTest {
	@Test
	void getValorTotal_ReturnaValorUnitarioMultiplicandoQuantidade_QuandoSucesso() {
		final Produto produto = new Produto(10L, "fake");
		final int quantidade = 10;
		final BigDecimal valorUnitario = BigDecimal.valueOf(1);
		final BigDecimal valorTotalEsperado = BigDecimal.valueOf(10);
		final Item itemFake = new Item(produto, valorUnitario, quantidade );

		final BigDecimal valorTotalResultado = itemFake.getValorTotal();

		assertThat(valorTotalResultado).isEqualTo(valorTotalEsperado);
	}
}