package com.dionlan.primeira.api.controller;

import static com.dionlan.primeira.infrastructure.repository.specification.RestauranteSpecs.comFreteGratis;
import static com.dionlan.primeira.infrastructure.repository.specification.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dionlan.primeira.domain.model.Cozinha;
import com.dionlan.primeira.domain.model.Restaurante;
import com.dionlan.primeira.domain.repository.CozinhaRepository;
import com.dionlan.primeira.domain.repository.RestauranteRepository;


@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;


	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> buscarPorNome(@RequestParam("nome") String nome){
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}

	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> consultarPorNome(String nome, Long idCozinha){
		return restauranteRepository.consultarPorNome(nome, idCozinha);
	}

	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> unicaPorNome(String nome){
		return cozinhaRepository.findByNome(nome);
	}

	@GetMapping("/cozinhas/exists") //verifica se um recurso existe, retorna true ou false
	public boolean cozinhaExiste(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}

	@GetMapping("/cozinhas/primeira") //verifica se um recurso existe, retorna true ou false
	public Optional<Cozinha> cozinhaPrimeira() {
		return cozinhaRepository.buscarPrimeiro();
	}

	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> retaurantePorTaxaFrete(BigDecimal taxaInicialFrete, BigDecimal taxaFinalFrete){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicialFrete, taxaFinalFrete);
	}

	@GetMapping("/restaurantes/primeiro-por-nome") //retorna apenas o primeiro nome
	public Optional<Restaurante> restaurantePrimeiroPorNome(String nome){
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}

	@GetMapping("/restaurantes/top2-por-nome") //retorna os dois primeiros TopN = N primeiros
	public List<Restaurante> restaurantesTop2PorNome(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}

	@GetMapping("/restaurantes/count-cozinhas")
	public int countCozinhas(Long idCozinha) {
		return restauranteRepository.countByCozinhaId(idCozinha);
	}

	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesFreteGratis(String nome){

		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}

	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro(){
		return restauranteRepository.buscarPrimeiro();
	}
}
