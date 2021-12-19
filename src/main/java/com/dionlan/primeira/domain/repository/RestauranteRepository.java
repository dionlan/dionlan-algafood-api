package com.dionlan.primeira.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dionlan.primeira.domain.model.Restaurante;

/**
 *
 * @author Dionlan
 * RestauranteRepository herda JpaRepository (repositório base) que herda de PaginAndSortingRepository que herda de CrudRepository que herda de Repository
 * Agora esta interface herda de CustomJpaRepository que heda de JpaRepository e assim por diante
 */
@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{


	@Override
	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();

	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id") //jpql para retornar restaurante(s) de uma determinada cozinha
	//ao inves de findTodasByNomeContaining
	List<Restaurante> consultarPorNome(String nome, @Param("restauranteId") Long idCozinha);
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);
}
