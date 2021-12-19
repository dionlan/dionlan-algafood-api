package com.dionlan.primeira.infrastructure.repository;
/*
package com.dionlan.primeira.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import com.dionlan.primeira.domain.model.Estado;
import com.dionlan.primeira.domain.repository.EstadoRepository;

@Controller
public class EstadoRepositoryImp implements EstadoRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Estado> listar(){
		return entityManager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Transactional
	@Override
	public Estado adicionar(Estado estado) {
		return entityManager.merge(estado);
	}

	@Override
	public Estado buscar(Long id) {
		return entityManager.find(Estado.class, id);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Estado estado = buscar(id);

		if(estado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		entityManager.remove(estado);
	}
} */
