package com.example.sdexample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.sdexample.domain.Customer;
import com.example.sdexample.domain.Product;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	public Customer findByFirstNameAndLastName(String firstName, String lastName);

	@Query("select distinct o.orderedBy from Order o join o.orderedItems as p where p = ?1")
	public List<Customer> findCustomersWhoPurchasedProduct(Product p);

}
