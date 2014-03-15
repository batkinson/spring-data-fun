package com.example.sdexample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.sdexample.domain.Customer;
import com.example.sdexample.domain.Order;

@RepositoryRestResource
public interface OrderDao extends JpaRepository<Order, Long> {

	public List<Order> findByOrderedBy(Customer orderedBy);

}
