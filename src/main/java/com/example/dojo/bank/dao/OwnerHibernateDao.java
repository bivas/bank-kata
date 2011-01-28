package com.example.dojo.bank.dao;

import java.util.Collection;

import javax.inject.Inject;

import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.example.dojo.bank.model.Owner;
import com.example.dojo.bank.model.OwnerVO;

@Repository
final class OwnerHibernateDao extends AbstractDao<Owner> implements OwnerDao {

	@Inject
	OwnerHibernateDao(final HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	@Override
	protected Class<? extends Owner> getPersistentClass() {
		return OwnerVO.class;
	}

	@Override
	public Owner findOwnerById(final long id) {
		Assert.isTrue(id > 0, "id must be greater than zero");
		return findEntityById(id);
	}

	@Override
	public Owner updateOwner(final Owner owner) {
		Assert.notNull(owner, "owner is required");
		return saveOrUpdate(owner);
	}

	@Override
	public Owner findOwnerBySocialSecurityNumber(final String socialSecurityNumber) {
		Assert.hasText(socialSecurityNumber, "social security number is required");
		return findUniqueByCriteria(Restrictions.eq("socialsecuritynumber", socialSecurityNumber));
	}

	@Override
	public Collection<Owner> findOwnersByGivenAndSurname(final String givenName, final String surname) {
		Assert.hasText(givenName, "givenName is required");
		Assert.hasText(surname, "surname is required");
		return findByCriteria(Restrictions.eq("givenname", givenName), Restrictions.eq("surname", surname));
	}

	@Override
	public Collection<Owner> findOwnersBySurname(final String surname) {
		Assert.hasText(surname, "surname is required");
		return findByCriteria(Restrictions.eq("surname", surname));
	}

}
