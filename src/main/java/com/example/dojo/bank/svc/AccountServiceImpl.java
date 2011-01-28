package com.example.dojo.bank.svc;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.dojo.bank.dao.AccountDao;
import com.example.dojo.bank.model.Account;
import com.example.dojo.bank.model.AccountVO;
import com.example.dojo.bank.model.Owner;

@Service
final class AccountServiceImpl implements AccountService {

	private final AccountDao accountDao;

	@Inject
	AccountServiceImpl(final AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public Account createAccount(final Owner owner, final int branch, final long number) {
		Assert.notNull(owner, "owner is required");
		assertNoExistingAccount(owner, branch, number);
		final Account account = new AccountVO(owner, branch, number, 0.0);
		return accountDao.updateAccount(account);
	}

	private void assertNoExistingAccount(final Owner owner, final int branch, final long number) {
		assertSingleAccountForOwner(owner);
		assertUniqueAccount(branch, number);
	}

	private void assertSingleAccountForOwner(final Owner owner) {
		Assert.isNull(findAccountByOwner(owner), "account already exist for owner");
	}

	private void assertUniqueAccount(final int branch, final long number) {
		Assert.isNull(findAccountByBranchAndNumber(branch, number), "account already exist for owner");
	}

	@Override
	public Account findAccountByBranchAndNumber(final int branch, final long number) {
		Assert.isTrue((branch > 0) && (number > 0), "branch and number must be greater than");
		return accountDao.findAccountByBranchAndNumber(branch, number);
	}

	@Override
	public Account findAccountByOwner(final Owner owner) {
		Assert.notNull(owner, "owner is required");
		return accountDao.findAccountByOwner(owner);
	}

	@Override
	public Account findAccountByOwnerSocialSecurityNumber(final String socialSecurityNumber) {
		Assert.hasText(socialSecurityNumber, "social security number is required");
		return accountDao.findAccountByOwnerSocialSecurityNumber(socialSecurityNumber);
	}

	@Override
	public Collection<Account> findAccountsByBranch(final int branch) {
		Assert.isTrue(branch > 0, "branch must be greater than");
		return accountDao.findAccountsByBranch(branch);
	}

	@Override
	public Collection<Account> findAccountsWithBalanceGreaterThan(final double amount) {
		Assert.isTrue(amount < Double.MAX_VALUE, "nothing greater than max double");
		return accountDao.findAccountsWithBalanceGreaterThan(amount);
	}

	@Override
	public void updateAccountBalance(final Account account, final double updatedBalance) {
		throw new NotImplementedException("don't use it yet");
	}

}
