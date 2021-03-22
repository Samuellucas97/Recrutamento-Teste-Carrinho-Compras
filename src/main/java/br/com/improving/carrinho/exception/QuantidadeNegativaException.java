package br.com.improving.carrinho.exception;

public class QuantidadeNegativaException extends RuntimeException {
	public QuantidadeNegativaException(String message) {
		super(message);
	}
}
