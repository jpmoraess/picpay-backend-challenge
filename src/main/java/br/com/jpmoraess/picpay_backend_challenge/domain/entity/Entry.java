package br.com.jpmoraess.picpay_backend_challenge.domain.entity;

import br.com.jpmoraess.picpay_backend_challenge.domain.exception.EntryDomainException;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;

import java.util.Objects;
import java.util.UUID;

public class Entry {

    private final UUID id;
    private final Long walletId;
    private final Money amount;

    private Entry(UUID id, Long walletId, Money amount) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
    }

    public static Entry create(Long walletId, Money amount) {
        if (walletId == null)
            throw new EntryDomainException("Wallet cannot be null");
        if (amount == null)
            throw new EntryDomainException("Invalid entry amount");
        return new Entry(UUID.randomUUID(), walletId, amount);
    }

    public static Entry restore(UUID id, Long walletId, Money amount) {
        if (id == null)
            throw new EntryDomainException("Entry ID cannot be null");
        if (walletId == null)
            throw new EntryDomainException("Wallet cannot be null");
        if (amount == null)
            throw new EntryDomainException("Invalid entry amount");
        return new Entry(id, walletId, amount);
    }

    public UUID getId() {
        return id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry that = (Entry) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
