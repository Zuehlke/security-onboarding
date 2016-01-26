package com.zuehlke.zrs_curriculum;

import java.util.Collection;
import java.util.UUID;

public interface TransactionLog {

	Collection<Transaction> getTransactions();

	Transaction addTransactionToLog(UUID fromAccountId, UUID toAccountId, int amount);
}
