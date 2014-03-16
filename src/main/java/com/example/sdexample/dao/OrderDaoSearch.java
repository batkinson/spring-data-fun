package com.example.sdexample.dao;

import java.util.List;

import com.example.sdexample.domain.Order;

public interface OrderDaoSearch {

	public List<Order> findByText(String searchText);

}
