package com.workspace.barbara;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {

	@Inject
	EntityManager em;

	@Transactional
	public void update(Categoria categoria) {
		em.merge(categoria);
	}

}
