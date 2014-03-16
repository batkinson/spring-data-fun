package com.example.sdexample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.example.sdexample.domain.Order;

public class OrderDaoSearchImpl implements OrderDaoSearch {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@SuppressWarnings("unchecked")
	public List<Order> findByText(String searchText) {
		EntityManager em = null;
		try {
			em = entityManagerFactory.createEntityManager();
			FullTextEntityManager ftEm = Search.getFullTextEntityManager(em);
			QueryBuilder qb = ftEm.getSearchFactory().buildQueryBuilder()
					.forEntity(Order.class).get();
			org.apache.lucene.search.Query luceneQuery = qb
					.keyword()
					.onFields("orderedBy.firstName", "orderedBy.lastName",
							"orderedItems.name").matching(searchText)
					.createQuery();
			Query jpaQuery = ftEm.createFullTextQuery(luceneQuery, Order.class);
			return jpaQuery.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
