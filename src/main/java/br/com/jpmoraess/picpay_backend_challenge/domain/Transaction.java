package br.com.jpmoraess.picpay_backend_challenge.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final UUID fromWalletId;
    private final UUID toWalletId;
    private final BigDecimal value;
    private final LocalDateTime dateTime;

    private Transaction(UUID id, UUID fromWalletId, UUID toWalletId, BigDecimal value, LocalDateTime dateTime) {
        this.id = id;
        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
        this.value = value;
        this.dateTime = dateTime;
    }

    public static Transaction createTransaction(UUID fromWalletId, UUID toWalletId, BigDecimal value, LocalDateTime dateTime) {
        if (fromWalletId == null)
            throw new IllegalArgumentException("From wallet cannot be null");
        if (toWalletId == null)
            throw new IllegalArgumentException("To wallet cannot be null");
        if (value.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Invalid transaction value");
        if (dateTime.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Invalid date time");
        return new Transaction(UUID.randomUUID(), fromWalletId, toWalletId, value, dateTime);
    }

    public UUID getId() {
        return id;
    }

    public UUID getFromWalletId() {
        return fromWalletId;
    }

    public UUID getToWalletId() {
        return toWalletId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
