package com.dionlan.primeira.jpa;

import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dionlan.primeira.DionlanApiApplication;
import com.dionlan.primeira.domain.model.Restaurante;
import com.dionlan.primeira.domain.repository.RestauranteRepository;

public class ConsultaRestaurantePorIdMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(DionlanApiApplication.class).web(WebApplicationType.NONE).run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		Optional<Restaurante> restaurante = restauranteRepository.findById(1L);

		System.out.println(restaurante);
	}
}
