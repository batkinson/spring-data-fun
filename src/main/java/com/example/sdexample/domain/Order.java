package com.example.sdexample.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"order\"")
public class Order {

	private Long id;
	private Date orderedOn;
	private Customer orderedBy;
	private Set<Product> orderedItems;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderedOn() {
		return orderedOn;
	}

	public void setOrderedOn(Date orderedOn) {
		this.orderedOn = orderedOn;
	}

	@ManyToOne
	public Customer getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(Customer orderedBy) {
		this.orderedBy = orderedBy;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	public Set<Product> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(Set<Product> orderedItems) {
		this.orderedItems = orderedItems;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderedOn=" + orderedOn + ", orderedBy="
				+ orderedBy + ", orderedItems=" + orderedItems + "]";
	}
}
