package br.com.jpmoraess.picpay_backend_challenge.domain.entity;

import br.com.jpmoraess.picpay_backend_challenge.domain.exception.TransferDomainException;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transfer {

    private final UUID id;
    private final Long payerId;
    private final Long payeeId;
    private final Money amount;
    private final LocalDateTime dateTime;

    private Transfer(UUID id, Long payerId, Long payeeId, Money amount, LocalDateTime dateTime) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public static Transfer create(Long payerId, Long payeeId, Money amount) {
        if (payerId == null)
            throw new TransferDomainException("Payer cannot be null");
        if (payeeId == null)
            throw new TransferDomainException("Payee cannot be null");
        if (!amount.isGreaterThanZero())
            throw new TransferDomainException("Invalid transfer amount");
        return new Transfer(UUID.randomUUID(), payerId, payeeId, amount, LocalDateTime.now());
    }

    public static Transfer restore(UUID id, Long payerId, Long payeeId, Money amount, LocalDateTime dateTime) {
        if (id == null)
            throw new TransferDomainException("Transfer ID cannot be null");
        if (payerId == null)
            throw new TransferDomainException("Payer cannot be null");
        if (payeeId == null)
            throw new TransferDomainException("Payee cannot be null");
        return new Transfer(id, payerId, payeeId, amount, dateTime);
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
        Transfer that = (Transfer) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
