package com.example.dojo.bank.model;

public interface Owner extends Entity{

	public String getSocialSecurityNumber();
	
	public String getSurname();
	
	public String getGivenName();
	
	public Gendre getGendre();
}
