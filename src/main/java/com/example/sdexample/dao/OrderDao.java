package com.example.sdexample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sdexample.domain.Customer;
import com.example.sdexample.domain.Order;

public interface OrderDao extends JpaRepository<Order, Long>, OrderDaoSearch {

	public List<Order> findByOrderedBy(Customer orderedBy);

}
