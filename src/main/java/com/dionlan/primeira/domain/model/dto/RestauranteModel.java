package com.dionlan.primeira.domain.model.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe para implementar o padrão de projeto DTO. Especifica quais propriedades serão apresentados na camada de apresentação
 * Classe que vai devolver apenas os objetos java populados para serem serializados em objeto json 
 * Classe para concentrar as anotações do jackson da classe Restaurante
 * 
 * {
 *		"id" : 1
 *		"nome" : "Comida Mineira",
 *		"cozinha" : {
 *					"id" : 1,
 *					"nome" : "Brasileira"
 *		}
 * }
 * @author Dionlan
 *
 * Evita-se a exposição das entidades para a API, DTO transfere apenas os objetos de interesse do cliente Rest
 * Pode-ser alterar os nomes das propriedades que será invisível para a entidade Restaurante que faz parte do modelo de domínio
 * Faz a correspondia da propriedade de destino com a destino pelo model match pos meio de tokens 
 * Origem: cozinha, nome
 * Destino: cozinha, nome
 * 
 */
@Setter
@Getter
public class RestauranteModel {
	
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaModel cozinha;
	
	/**
	 * Origem: cozinha, nome
	 * Destino: nome, cozinha
	 * 	
	 	private String nomeCozinha;
		private long idCozinha;
	 * 
	 */
}
