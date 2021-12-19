package com.dionlan.primeira.infrastructure.repository.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.dionlan.primeira.domain.model.Restaurante;

/**
 *
 * @author Dionlan
 * Fábrica que cria as specifications
 *
 */
public class RestauranteSpecs {

	public static Specification<Restaurante> comFreteGratis(){
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO); //classe anônima
	}

	public static Specification<Restaurante> comNomeSemelhante(String nome){
		return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%"); //classe anônima
	}
}
