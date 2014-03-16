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

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "\"order\"")
public class Order {

	private Long id;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@DateBridge(resolution = Resolution.DAY)
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

	@IndexedEmbedded
	@ManyToOne
	public Customer getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(Customer orderedBy) {
		this.orderedBy = orderedBy;
	}

	@IndexedEmbedded
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
