package com.dionlan.primeira.infrastructure.repository;
/*package com.dionlan.primeira.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import com.dionlan.primeira.domain.model.Cozinha;
import com.dionlan.primeira.domain.repository.CozinhaRepository;

@Controller
public class CozinhaRepositoryImp implements CozinhaRepository{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Cozinha> todas() {
		return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	public List<Cozinha> buscarPorNome(String nome){
		return entityManager.createQuery("from Cozinha where nome like :nome", Cozinha.class).setParameter("nome", "%" + nome + "%").getResultList();
	}

	@Override
	public Cozinha buscar(Long id) {
		return entityManager.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public Cozinha adicionar(Cozinha cozinha) {
		return entityManager.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);

		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		entityManager.remove(cozinha);
	}
}
*/