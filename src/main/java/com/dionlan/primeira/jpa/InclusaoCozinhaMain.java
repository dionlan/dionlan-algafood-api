package com.dionlan.primeira.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dionlan.primeira.DionlanApiApplication;
import com.dionlan.primeira.domain.model.Cozinha;
import com.dionlan.primeira.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(DionlanApiApplication.class).web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Japonesa");

		cozinhaRepository.save(cozinha1);
		cozinhaRepository.save(cozinha2);
	}
}
