package com.dionlan.primeira.domain.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

/**
 * Existem propriedades que são somente para leitura, esta classe modela as propriedades de entradas de dados do endpoint
 * Classe para representar as entradas (recebendo na requisição HTTP) de dados. Local onde a API vai visualizar. Dqui passa para o Modelo
 * O Modelo não conhece as requisições da API
 * @author Dionlan
 *
 */
@Setter
@Getter
public class RestauranteInputModel {

	@NotBlank
	private String nome;
	
	@NotNull
	@PositiveOrZero 
	private BigDecimal taxaFrete;
	
	//Modelagem da cozinha recebida no corpo do restaurante
	@Valid
	@NotNull
	private CozinhaReferenciaIdInput cozinha;
}
