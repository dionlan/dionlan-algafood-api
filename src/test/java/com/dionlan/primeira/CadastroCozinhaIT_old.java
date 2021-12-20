package com.dionlan.primeira;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dionlan.primeira.domain.exception.EntidadeEmUsoException;
import com.dionlan.primeira.domain.exception.EntidadeNaoEncontradaException;
import com.dionlan.primeira.domain.model.Cozinha;
import com.dionlan.primeira.domain.service.CadastroCozinhaService;
/**
 * IT na classe = Integration Test do maven-failsafe-plugin (padrão adicionado no final da classe para identificar testes de integração)
 * Objetivo: identificar e separar o testes de integração com os testes unitários
 * @author Dionlan
 *
 */
@SpringBootTest
class CadastroCozinhaIT_old {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		// ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		// validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
		
	}
	
	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome(null);
			novaCozinha = cadastroCozinha.salvar(novaCozinha);
		});
	}
	/**
	 * O teste consite em tentar excluir uma cozinha que esteja em uso por 1 ou mais restaurantes
	 * O teste apresenta sucesso se cair na exceção de EntidadeEmUsoException, que informa que não pode ser excluida por estar em uso
	 * O teste falha se a exceção não for tratada corretamente (ex: passar outra classe de Exception como a EntidadeNaoEncontrada)
	 * 
	 */
	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
			cadastroCozinha.excluir(1L); //Cozinha com id 1 não pode ser excluída pois está cadastrada e relacioada com restaurantes, logo o teste deve falhar
			
		});
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			cadastroCozinha.excluir(10L); //Cozinha com id 10 não pode ser excluída pois não está cadastrada, logo não será encontrada
		});
	}
}
