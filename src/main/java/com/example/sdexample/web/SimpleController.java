package com.example.sdexample.web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.sdexample.dao.CustomerDao;
import com.example.sdexample.domain.Customer;
import com.example.sdexample.service.CustomerService;

@Controller
@RequestMapping("/")
public class SimpleController {

	private CustomerDao customerDao;
	private CustomerService customerService;

	@Autowired
	public SimpleController(CustomerDao customerDao,
			CustomerService customerService) {
		this.customerDao = customerDao;
		this.customerService = customerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void doIt(HttpServletResponse rsp) throws IOException {

		customerService.createRandomCustomer();

		Writer out = rsp.getWriter();

		for (Customer c : customerDao.findAll()) {
			out.write(c.toString());
			out.write("\n");
		}
	}
}
