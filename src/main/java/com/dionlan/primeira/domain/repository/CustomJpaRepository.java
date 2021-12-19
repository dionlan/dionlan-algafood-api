package com.dionlan.primeira.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * Cria um repositório genérico
 * @author Dionlan
 * Interface que cria métodos como se fosse no repositório base (SimpleRepositoryJpa.class) e pode ser utilizado em todo os demais repositórios
 * Ex.: buscar o primeiro registro no banco - customiza o repositório base
 *
 */
@NoRepositoryBean //Spring Data JPA não deve instanciar uma implementação para esta interface
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID>{ //estudar generics para implementar uma interface tipada; T é o tipo da entidade

	Optional<T> buscarPrimeiro();

}
