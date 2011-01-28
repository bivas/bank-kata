package com.example.dojo.bank.dao;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.lang.NotImplementedException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.example.dojo.bank.model.Account;
import com.example.dojo.bank.model.AccountVO;
import com.example.dojo.bank.model.Owner;

@Repository
final class AccountHibernateDao extends AbstractDao<Account> implements AccountDao{

	@Inject
	AccountHibernateDao(final HibernateTemplate hibernateTemplate) {
		super(hibernateTemplate);
	}

	@Override
	protected Class<? extends Account> getPersistentClass() {
		return AccountVO.class;
	}

	@Override
	public Account findAccountByBranchAndNumber(final int branch, final long number) {
		Assert.isTrue((branch > 0) && (number > 0), "branch and number must be greater than zero");
		return findUniqueByCriteria(Restrictions.eq("branch", branch), Restrictions.eq("number", number));
	}

	@Override
	public Account updateAccount(final Account account) {
		Assert.notNull(account, "account is required");
		return saveOrUpdate(account);
	}

	@Override
	public Account findAccountById(final long id) {
		Assert.isTrue(id > 0, "id must be greater than zero");
		return findEntityById(id);
	}

	@Override
	public Account findAccountByOwner(final Owner owner) {
		Assert.notNull(owner, "owner is null");
		return findUniqueByCriteria(Restrictions.eq("owner", owner));
	}

	@Override
	public Collection<Account> findAccountsByBranch(final int branch) {
		Assert.isTrue(branch > 0, "branch must be greater than zero");
		return findByCriteria(Restrictions.eq("branch", branch));
	}

	@Override
	public Account findAccountByOwnerSocialSecurityNumber(final String socialSecurityNumber) {
		Assert.hasText(socialSecurityNumber, "social secutiry number is required");
		final Query hql = getCurrentSession().createQuery("from Account a where a.owner.socialsecuritynumber = :ssn");
		hql.setString("ssn", socialSecurityNumber);
		return getPersistentClass().cast(hql.uniqueResult());
	}

	@Override
	public Collection<Account> findAccountsWithBalanceGreaterThan(final double amount) {
		throw new NotImplementedException("don't use it yet");
	}

}
