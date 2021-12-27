package com.dionlan.primeira;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.dionlan.primeira.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class) //repositório base não é mais o SimpleJpaRepository e sim o CustomJpaRepositoruImpl (que é uma especialização)
public class DionlanApiApplication {

	 public static void main(String[] args) {
		 /*
		Cliente joao = new Cliente("joao", "joao@wyz.com.br", "619982551566");
		Cliente maria = new Cliente("maria", "maria@xyz,com", "61984755688");

		NotificadorEmail noticador = new NotificadorEmail();
		AtivacaoClienteService ativacaoCliente = new AtivacaoClienteService(noticador);
		ativacaoCliente.ativar(joao);
		ativacaoCliente.ativar(maria);
		**/
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(DionlanApiApplication.class, args);
	}
}
