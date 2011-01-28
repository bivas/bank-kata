package com.example.dojo.bank.promotion;

import java.util.Collection;

import com.example.dojo.bank.model.Account;
import com.example.dojo.bank.model.Owner;

public interface PromotionService {

	public void applyPromotion(Owner owner, Bonus bonus);
	
	public Collection<Account> findRichPeople();
}
