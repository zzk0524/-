package com.sdut.util;

public class PriceByGoodssort {

	String goodssort;
	double price;
	public String getGoodssort() {
		return goodssort;
	}
	public void setGoodssort(String goodssort) {
		this.goodssort = goodssort;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PriceByGoodssort [goodssort=" + goodssort + ", price=" + price + "]";
	}
	
}
