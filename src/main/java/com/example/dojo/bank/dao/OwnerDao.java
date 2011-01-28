package com.example.dojo.bank.dao;

import java.util.Collection;

import com.example.dojo.bank.model.Owner;

public interface OwnerDao {

	public Owner findOwnerById(long id);
	public Owner findOwnerBySocialSecurityNumber(String socialSecurityNumber);
	
	public Collection<Owner> findOwnersByGivenAndSurname(String givenName, String surname);
	public Collection<Owner> findOwnersBySurname(String surname);
	public Owner updateOwner(Owner owner);
}
