package com.example.dojo.bank.svc;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.dojo.bank.dao.OwnerDao;
import com.example.dojo.bank.model.Gendre;
import com.example.dojo.bank.model.Owner;
import com.example.dojo.bank.model.OwnerVO;

@Service
final class OwnerServiceImpl implements OwnerService {

	private final OwnerDao ownerDao;

	@Inject
	OwnerServiceImpl(final OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}

	@Override
	public Owner createOwner(final String socialSecurityNumber, final String givenName,	final String surname, final Gendre gendre) {
		Assert.hasText(socialSecurityNumber, "social security number is required");
		Assert.hasText(givenName, "givenName is required");
		Assert.hasText(surname, "surname is required");
		Assert.notNull(gendre, "gendre is required");
		assertOwnerNotExist(socialSecurityNumber);
		final Owner owner = new OwnerVO(socialSecurityNumber, givenName, surname, gendre);
		return ownerDao.updateOwner(owner);
	}

	private void assertOwnerNotExist(final String socialSecurityNumber) {
		Assert.state(findOwnerBySocialSecurityNumber(socialSecurityNumber) == null, "owner already exist");
	}

	@Override
	public Owner findOwnerBySocialSecurityNumber(final String socialSecurityNumber) {
		Assert.hasText(socialSecurityNumber, "social security number is required");
		return ownerDao.findOwnerBySocialSecurityNumber(socialSecurityNumber);
	}

	@Override
	public Collection<Owner> findOwnersBySurname(final String surname) {
		Assert.hasText(surname, "surname is required");
		return ownerDao.findOwnersBySurname(surname);
	}

}
