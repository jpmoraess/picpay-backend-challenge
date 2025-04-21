package br.com.jpmoraess.picpay_backend_challenge.domain.entity;

import br.com.jpmoraess.picpay_backend_challenge.domain.exception.TransactionDomainException;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final Long payerId;
    private final Long payeeId;
    private final Money amount;
    private final LocalDateTime dateTime;

    private Transaction(UUID id, Long payerId, Long payeeId, Money amount, LocalDateTime dateTime) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public static Transaction create(Long payerId, Long payeeId, Money amount) {
        if (payerId == null)
            throw new TransactionDomainException("Payer cannot be null");
        if (payeeId == null)
            throw new TransactionDomainException("Payee cannot be null");
        if (!amount.isGreaterThanZero())
            throw new TransactionDomainException("Invalid transaction amount");
        return new Transaction(UUID.randomUUID(), payerId, payeeId, amount, LocalDateTime.now());
    }

    public static Transaction restore(UUID id, Long payerId, Long payeeId, Money amount, LocalDateTime dateTime) {
        if (id == null)
            throw new TransactionDomainException("Transaction ID cannot be null");
        if (payerId == null)
            throw new TransactionDomainException("Payer cannot be null");
        if (payeeId == null)
            throw new TransactionDomainException("Payee cannot be null");
        return new Transaction(id, payerId, payeeId, amount, dateTime);
    }

    public UUID getId() {
        return id;
    }

    public Long getPayerId() {
        return payerId;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public Money getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
