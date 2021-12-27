package com.dionlan.primeira.domain.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaModel {

	private Long id;
	
	/**
	 * A ordem não importa, propriedades são dividas em tokens e comparadas para possibilitar que os nomes sejam relacionados
	 * Origem: cozinha, nome
	 * Destino: cozinha, nome
	 * Ex: cozinhaDescricao não funcionaria
	 */
	private String nome;
}
