package com.dionlan.primeira.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dionlan.primeira.DionlanApiApplication;
import com.dionlan.primeira.domain.model.Restaurante;
import com.dionlan.primeira.domain.repository.RestauranteRepository;

public class BuscaTodosRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(DionlanApiApplication.class).web(WebApplicationType.NONE).run(args);

		RestauranteRepository todosRestaurante = applicationContext.getBean(RestauranteRepository.class);
		List<Restaurante> restaurantes = todosRestaurante.findAll();

		for (Restaurante restaurante : restaurantes) {
			System.out.printf("%s - %f - %s \n", restaurante.getNome(), restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
		}
	}
}
