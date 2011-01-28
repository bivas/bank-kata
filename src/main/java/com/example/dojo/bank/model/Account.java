package com.example.dojo.bank.model;


public interface Account extends Entity{

	public Owner getOwner();
	
	public int getBranch();
	
	public long getNumber();
	
	public double getBalance();
	
}
