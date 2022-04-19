package com.deverything.candidate.productstore.pojo;

public final class ProductObject {
	
	private int id;
	private String name;
	private double price;
	

	/**
	 * @param id
	 * @param name
	 * @param price
	 */
	public ProductObject(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}


	@Override
	public String toString() {
		return "ProductObject [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
	
	

}
