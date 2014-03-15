package com.example.sdexample.web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.sdexample.dao.CustomerDao;
import com.example.sdexample.dao.OrderDao;
import com.example.sdexample.dao.ProductDao;
import com.example.sdexample.domain.Customer;
import com.example.sdexample.domain.Order;
import com.example.sdexample.domain.Product;

@Controller
@RequestMapping(value = "/")
public class SimpleController {

	private CustomerDao customerDao;
	private ProductDao productDao;
	private OrderDao orderDao;

	@Autowired
	public SimpleController(CustomerDao customerDao, ProductDao productDao,
			OrderDao orderDao) {
		this.customerDao = customerDao;
		this.productDao = productDao;
		this.orderDao = orderDao;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void summary(HttpServletResponse rsp) throws IOException {

		Writer out = rsp.getWriter();

		out.write("Customers\n");
		out.write("---------\n");
		for (Customer c : customerDao.findAll()) {
			out.write(c.toString());
			out.write("\n");
		}

		out.write("\nProducts\n");
		out.write("--------\n");
		for (Product p : productDao.findAll()) {
			out.write(p.toString());
			out.write("\n");
		}

		out.write("\nOrders\n");
		out.write("------\n");
		for (Order o : orderDao.findAll()) {
			out.write(o.toString());
			out.write("\n");
		}
	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
	public void customer(@PathVariable("id") Customer customer,
			HttpServletResponse rsp) throws IOException {

		if (customer == null) {
			rsp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		Writer out = rsp.getWriter();

		out.write(customer.toString());
		out.write("\n");

		out.write("\nItems Bought\n");
		out.write("------------\n");
		for (Product p : productDao.findProductsOrderedByCustomer(customer)) {
			out.write(p.toString());
			out.write("\n");
		}
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public void product(@PathVariable("id") Product product,
			HttpServletResponse rsp) throws IOException {

		if (product == null) {
			rsp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		Writer out = rsp.getWriter();

		out.write(product.toString());
		out.write("\n");

		out.write("\nWho Bought\n");
		out.write("----------\n");
		for (Customer c : customerDao.findCustomersWhoPurchasedProduct(product)) {
			out.write(c.toString());
			out.write("\n");
		}
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public void order(@PathVariable("id") Order order, HttpServletResponse rsp)
			throws IOException {

		if (order == null) {
			rsp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		Writer out = rsp.getWriter();

		out.write(order.toString());
		out.write("\n");

		out.write("\nOther Orders\n");
		out.write("------------\n");
		for (Order o : orderDao.findByOrderedBy(order.getOrderedBy())) {
			if (o.getId().equals(order.getId()))
				continue;
			out.write(o.toString());
			out.write("\n");
		}
	}

}
