package com.dionlan.primeira.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dionlan.primeira.DionlanApiApplication;
import com.dionlan.primeira.domain.model.Cozinha;
import com.dionlan.primeira.domain.repository.CozinhaRepository;

public class BuscaTodasCozinhasMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(DionlanApiApplication.class).web(WebApplicationType.NONE).run(args);

		CozinhaRepository todasCozinha = applicationContext.getBean(CozinhaRepository.class);
		List<Cozinha> cozinhas = todasCozinha.findAll();

		for (Cozinha cozinha : cozinhas) {
			System.out.println(cozinha.getNome());
		}
	}
}
