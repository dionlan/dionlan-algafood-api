package com.dionlan.primeira.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.dionlan.primeira.DionlanApiApplication;
import com.dionlan.primeira.domain.model.Estado;
import com.dionlan.primeira.domain.repository.EstadoRepository;

public class InclusaoEstadoMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(DionlanApiApplication.class).web(WebApplicationType.NONE).run(args);

		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

		Estado estado1 = new Estado();
		estado1.setNome("São Paulo");

		Estado estado2 = new Estado();
		estado2.setNome("Rio de Janeiro");

		estadoRepository.save(estado1);
		estadoRepository.save(estado2);
	}
}
