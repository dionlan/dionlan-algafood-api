package com.dionlan.primeira.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dionlan.primeira.domain.model.Cozinha;
import com.dionlan.primeira.domain.repository.CozinhaRepository;
import com.dionlan.primeira.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long cozinhaId) {
		return cadastroCozinhaService.buscarOuFalhar(cozinhaId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {

		return cadastroCozinhaService.salvar(cozinha);

	}

	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable("cozinhaId") Long cozinhaId, @RequestBody @Valid Cozinha cozinha) {
		
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id"); //copia (replace/substitui) as os valores das propriedade de cozinha vindas no parametro do atualizar para a cozinha atual.
		return cadastroCozinhaService.salvar(cozinhaAtual);

	}

	/**
	 * Resposabilide da camada de apresentação de tratar as requisições HTTP e lançar a exceção de resposta no status http
	 * @param cozinhaId
	 */
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId){
		cadastroCozinhaService.excluir(cozinhaId);
	}
}
