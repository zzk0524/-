package com.sdut.bean;

import java.util.Date;

public class GoodsList {

	int id;
	String goodsid;
	String goodsname;
	String goodssort;
	String state;
	int number;
	double price;
	Date time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "GoodsList [id=" + id + ", goodsid=" + goodsid + ", goodsname=" + goodsname + ", goodssort=" + goodssort
				+ ", price=" + price + ", state=" + state + ", number=" + number + ", time=" + time + "]";
	}
	
}
