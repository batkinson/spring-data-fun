package com.example.sdexample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sdexample.dao.CustomerDao;
import com.example.sdexample.domain.Customer;
import com.example.sdexample.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final String[] FIRST_NAMES = { "Rowan", "Addison", "Carmen",
			"Imogen", "Oscar" };
	private static final String[] LAST_NAMES = { "Atkinson", "Dussault", "Day",
			"Gosseline" };

	private CustomerDao customerDao;

	@Autowired
	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Transactional
	public void createRandomCustomer() {
		Customer c = new Customer();
		c.setFirstName(FIRST_NAMES[(int) (Math.random() * FIRST_NAMES.length)]);
		c.setLastName(LAST_NAMES[(int) (Math.random() * LAST_NAMES.length)]);
		customerDao.save(c);
	}

}
