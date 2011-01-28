package com.example.dojo.bank.dao;

import java.util.Collection;

import com.example.dojo.bank.model.Account;
import com.example.dojo.bank.model.Owner;

public interface AccountDao {

	public Account findAccountById(long id);
	public Account updateAccount(Account account);

	public Account findAccountByBranchAndNumber(int branch, long number);
	public Account findAccountByOwner(Owner owner);

	public Collection<Account> findAccountsByBranch(int branch);

	public Account findAccountByOwnerSocialSecurityNumber(String socialSecurityNumber);
	public Collection<Account> findAccountsWithBalanceGreaterThan(double amount);


}
