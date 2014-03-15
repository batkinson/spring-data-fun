package com.example.sdexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sdexample.domain.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
}
