package com.example.dojo.bank.promotion;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Collections;

import org.easymock.Capture;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.example.dojo.bank.model.Account;
import com.example.dojo.bank.model.Owner;
import com.example.dojo.bank.svc.AccountService;
import com.example.dojo.test.AbstractMockAwareTest;

public class PromotionServiceImplTest extends AbstractMockAwareTest{

	private IMocksControl mocksControl;

	private AccountService mockAccountService;
	private PromotionService tested;

	@Before
	public void setUp() throws Exception {
		mocksControl = createControl();
		mockAccountService = mocksControl.createMock(AccountService.class);

		tested = new PromotionServiceImpl(mockAccountService);
	}

	@After
	public void tearDown() throws Exception {
		tested = null;
	}

	@Test
	public void testApplyPromotion() {
		final Owner mockOwner = mocksControl.createMock(Owner.class);
		final Account mockAccount = mocksControl.createMock(Account.class);
		final Bonus mockBonus = mocksControl.createMock(Bonus.class);

		final Capture<Double> updatedBalance = new Capture<Double>();

		expect(mockAccount.getBalance()).andReturn(100.0);
		expect(mockBonus.getBonusAmount()).andReturn(50.0);

		expect(mockAccountService.findAccountByOwner(mockOwner)).andReturn(mockAccount);
		mockAccountService.updateAccountBalance(eq(mockAccount), capture(updatedBalance));
		expectLastCall();
		mocksControl.replay();
		tested.applyPromotion(mockOwner, mockBonus);

		assertEquals("wrong balance", 150.0, updatedBalance.getValue().doubleValue(), 0.0);
		mocksControl.verify();
	}

	@Test
	public void testApplyPromotionShouldFail() throws Exception {
		final Owner mockOwner = mocksControl.createMock(Owner.class);
		final Bonus mockBonus = mocksControl.createMock(Bonus.class);
		expect(mockAccountService.findAccountByOwner(mockOwner)).andReturn(null);
		mocksControl.replay();

		try {
			tested.applyPromotion(mockOwner, mockBonus);
			fail("missing acccount - should fail");
		} catch (final IllegalStateException e) {
			// good
		}

		mocksControl.verify();
	}

	@Test
	public void testFindRichPeople() {
		final Account mockAccount = mocksControl.createMock(Account.class);
		final Collection<Account> accounts = Collections.singleton(mockAccount);
		expect(mockAccountService.findAccountsWithBalanceGreaterThan(1000000)).andReturn(accounts);

		mocksControl.replay();
		final Collection<Account> result = tested.findRichPeople();
		assertFalse("empty or null result", CollectionUtils.isEmpty(result));
		assertEquals("size", 1, result.size());
		assertSame("account", mockAccount, result.iterator().next());
	}

}
