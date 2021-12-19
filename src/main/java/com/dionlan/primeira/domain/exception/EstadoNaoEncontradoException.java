package com.dionlan.primeira.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe cadastro de Estado para com código %d ", estadoId));
	}
}
