package com.example.dojo.bank.svc;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.easymock.Capture;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.example.dojo.bank.dao.AccountDao;
import com.example.dojo.bank.model.Account;
import com.example.dojo.bank.model.Owner;
import com.example.dojo.test.AbstractMockAwareTest;

@Ignore
public class AccountServiceImplTest extends AbstractMockAwareTest {

	private static final long NUMBER = 1000;

	private static final int BRANCH = 20;

	private IMocksControl mocksControl;

	private AccountDao mockAccountDao;
	private AccountService tested;

	@Before
	public void setUp() throws Exception {
		mocksControl = createControl();
		mockAccountDao = mocksControl.createMock(AccountDao.class);

		tested = new AccountServiceImpl(mockAccountDao);
	}

	@After
	public void tearDown() throws Exception {
		tested = null;
	}

	@Test
	public void testCreateAccount() {
		final Owner mockOwner = mocksControl.createMock(Owner.class);
		final Account mockAccount = mocksControl.createMock(Account.class);
		final Capture<Account> accountCapture = new Capture<Account>();

		expect(mockAccountDao.findAccountByOwner(mockOwner)).andReturn(null);
		expect(mockAccountDao.findAccountByBranchAndNumber(BRANCH, NUMBER)).andReturn(null);
		expect(mockAccountDao.updateAccount(capture(accountCapture))).andReturn(mockAccount );

		mocksControl.replay();
		final Account result = tested.createAccount(mockOwner , BRANCH, NUMBER);
		assertNotNull("result", result);

		mocksControl.verify();
		final Account account = accountCapture.getValue();
		assertNotNull("account", account);
		assertSame("owner", mockOwner, account.getOwner());
		assertEquals("branch", BRANCH, account.getBranch());
		assertEquals("number", NUMBER, account.getNumber());
		assertEquals("balance", 0.0, account.getBalance(), 0.0);
	}

	@Test
	public void testCreateAccountShouldFailExistingOwner() {
		final Owner mockOwner = mocksControl.createMock(Owner.class);
		final Account mockAccount = mocksControl.createMock(Account.class);

		expect(mockAccountDao.findAccountByOwner(mockOwner)).andReturn(mockAccount);

		mocksControl.replay();

		try {
			tested.createAccount(mockOwner, BRANCH, NUMBER);
		} catch (final IllegalArgumentException e) {
			// good
		}

		mocksControl.verify();
	}

	@Test
	public void testFindAccountByBranchAndNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAccountByOwner() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAccountByOwnerSocialSecurityNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAccountsByBranch() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAccountsWithBalanceGreaterThan() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAccountBalance() {
		fail("Not yet implemented");
	}

}
