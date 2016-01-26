package com.zuehlke.zrs_curriculum;

import java.util.*;

public class TransactionLogImpl implements TransactionLog {

	private final List<Transaction> transactions = new ArrayList<>();

	@Override
	public Collection<Transaction> getTransactions() {
		return Collections.unmodifiableCollection(transactions);
	}

	@Override
	public Transaction addTransactionToLog(UUID fromAccountId, UUID toAccountId, int amount) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
