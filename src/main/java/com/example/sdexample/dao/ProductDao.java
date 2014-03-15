package com.example.sdexample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sdexample.domain.Customer;
import com.example.sdexample.domain.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

	@Query("select p from Order o join o.orderedItems as p where o.orderedBy = :customer")
	List<Product> findProductsOrderedByCustomer(Customer customer);
}
