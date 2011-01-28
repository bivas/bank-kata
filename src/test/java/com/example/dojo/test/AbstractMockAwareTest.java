package com.example.dojo.test;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;

public abstract class AbstractMockAwareTest {

	protected final IMocksControl createNiceControl() {
		return EasyMock.createNiceControl();
	}
	
	protected final IMocksControl createControl() {
		return EasyMock.createControl();
	}
	
	protected final IMocksControl createStrictControl() {
		return EasyMock.createStrictControl();
	}
}
