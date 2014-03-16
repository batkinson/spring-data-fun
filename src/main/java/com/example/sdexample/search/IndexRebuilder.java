package com.example.sdexample.search;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

public class IndexRebuilder {

	EntityManagerFactory entityManagerFactory;

	public IndexRebuilder(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void rebuildIndex() throws InterruptedException {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			FullTextEntityManager fullTextEntityManager = Search
					.getFullTextEntityManager(entityManager);
			fullTextEntityManager.createIndexer().startAndWait();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
}
