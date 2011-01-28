package com.example.dojo.bank.promotion;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.dojo.bank.model.Account;
import com.example.dojo.bank.model.Owner;
import com.example.dojo.bank.svc.AccountService;

@Service
final class PromotionServiceImpl implements PromotionService {

	private static final long MILLION = 1000000;

	private final AccountService accountService;

	@Inject
	PromotionServiceImpl(final AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void applyPromotion(final Owner owner, final Bonus bonus) {
		final Account account = accountService.findAccountByOwner(owner);
		Assert.state(account != null, "account doesn't exist");
		final double updatedBalance = account.getBalance() + bonus.getBonusAmount();
		accountService.updateAccountBalance(account, updatedBalance);
	}

	@Override
	public Collection<Account> findRichPeople() {
		return accountService.findAccountsWithBalanceGreaterThan(MILLION);
	}

}
