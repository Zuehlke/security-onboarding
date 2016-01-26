package com.zuehlke.zrs_curriculum;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

	private final UUID id;
	private final LocalDateTime timestamp;
	private final UUID accountFromId;
	private final UUID accountToId;
	private final int amount;

	public Transaction(UUID accountFromId, UUID accountToId, int amount) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public UUID getId() {
		return id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public UUID getAccountFromId() {
		return accountFromId;
	}

	public UUID getAccountToId() {
		return accountToId;
	}

	public int getAmount() {
		return amount;
	}
}
