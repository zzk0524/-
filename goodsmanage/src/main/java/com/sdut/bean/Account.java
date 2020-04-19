package com.sdut.bean;

public class Account {

	int id;
	String account;
	String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "account [id=" + id + ", account=" + account + ", password=" + password + "]";
	}
	
}
