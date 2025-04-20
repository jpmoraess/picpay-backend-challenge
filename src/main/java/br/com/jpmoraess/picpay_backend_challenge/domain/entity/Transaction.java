package br.com.jpmoraess.picpay_backend_challenge.domain.entity;

import br.com.jpmoraess.picpay_backend_challenge.domain.exception.TransactionDomainException;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final UUID payerId;
    private final UUID payeeId;
    private final Money value;
    private final LocalDateTime dateTime;

    private Transaction(UUID id, UUID payerId, UUID payeeId, Money value, LocalDateTime dateTime) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.value = value;
        this.dateTime = dateTime;
    }

    public static Transaction create(UUID payerId, UUID payeeId, Money value, LocalDateTime dateTime) {
        if (payerId == null)
            throw new TransactionDomainException("Payer cannot be null");
        if (payeeId == null)
            throw new TransactionDomainException("Payee cannot be null");
        if (!value.isGreaterThanZero())
            throw new TransactionDomainException("Invalid transaction value");
        if (dateTime == null || dateTime.isBefore(LocalDateTime.now()))
            throw new TransactionDomainException("Invalid date time");
        return new Transaction(UUID.randomUUID(), payerId, payeeId, value, dateTime);
    }

    public static Transaction restore(UUID id, UUID payerId, UUID payeeId, Money value, LocalDateTime dateTime) {
        if (id == null)
            throw new TransactionDomainException("Transaction ID cannot be null");
        if (payerId == null)
            throw new TransactionDomainException("Payer cannot be null");
        if (payeeId == null)
            throw new TransactionDomainException("Payee cannot be null");
        return new Transaction(id, payerId, payeeId, value, dateTime);
    }

    public UUID getId() {
        return id;
    }

    public UUID getPayerId() {
        return payerId;
    }

    public UUID getPayeeId() {
        return payeeId;
    }

    public Money getValue() {
        return value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
