package com.example.dojo.bank.svc;

import java.util.Collection;

import com.example.dojo.bank.model.Gendre;
import com.example.dojo.bank.model.Owner;

public interface OwnerService {

	public Owner createOwner(String socialSecurityNumber, String givenName, String surname, Gendre gendre);
	public Owner findOwnerBySocialSecurityNumber(String socialSecurityNumber);
	
	public Collection<Owner> findOwnersBySurname(String surname);
}
