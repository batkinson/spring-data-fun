package com.example.sdexample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.sdexample.domain.Customer;
import com.example.sdexample.domain.Product;

@RepositoryRestResource
public interface ProductDao extends JpaRepository<Product, Long> {

	@Query("select distinct p from Order o join o.orderedItems as p where o.orderedBy = ?1")
	List<Product> findProductsOrderedByCustomer(Customer customer);

}
