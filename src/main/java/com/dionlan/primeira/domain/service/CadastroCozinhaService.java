package com.dionlan.primeira.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dionlan.primeira.domain.exception.CozinhaNaoEncontradaException;
import com.dionlan.primeira.domain.exception.EntidadeEmUsoException;
import com.dionlan.primeira.domain.model.Cozinha;
import com.dionlan.primeira.domain.repository.CozinhaRepository;

/**
 *
 * @author Dionlan Camada de abstração das funcionalidades principais, onde
 *         ficarão implementadas as regras de negócio de cada método Ex:
 *         cadastrar apenas cozinhas em dia útil, ou cozinhas ocidentais. Bem
 *         como o tratamento de excessões Tira a responsabilidade do controlador
 *         alterar o estado, para não ter acesso ao repositório
 *         (cozinhaRepository)
 */
@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida pois está em uso.";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	/**
	 * classe de serviço (regras de negócio) que não sabe nada da camada de apresentação (requisições HTTP), apenas
	 * apresenta a mensagem de exceção de negócio, e a controller trata as requisições http do clients
	 * @param id
	 */
	public void excluir(Long cozinhaId) {
		try {

			if (cozinhaId != null) {
				cozinhaRepository.deleteById(cozinhaId);
			}

		} catch (EmptyResultDataAccessException e ) {
			throw new CozinhaNaoEncontradaException(cozinhaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, cozinhaId));

		}
	}

	/*
	 * Busca a cozinha, caso não exista, lança a exception de entidade não encontrada
	 */
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}
}
