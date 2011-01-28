package com.example.dojo.bank.svc;

import java.util.Collection;

import com.example.dojo.bank.model.Account;
import com.example.dojo.bank.model.Owner;

public interface AccountService {

	public Account createAccount(Owner owner, int branch, long number);
	public Account findAccountByBranchAndNumber(int branch, long number);
	public Collection<Account> findAccountsByBranch(int branch);
	
	public Account findAccountByOwnerSocialSecurityNumber(String socialSecurityNumber);
	public Account findAccountByOwner(Owner owner);
	public Collection<Account> findAccountsWithBalanceGreaterThan(double amount);
	public void updateAccountBalance(Account account, double updatedBalance);
}
