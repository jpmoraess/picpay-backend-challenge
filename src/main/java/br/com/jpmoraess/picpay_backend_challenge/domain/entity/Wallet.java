package br.com.jpmoraess.picpay_backend_challenge.domain.entity;

import br.com.jpmoraess.picpay_backend_challenge.domain.exception.WalletDomainException;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Wallet {

    private final Long id;
    private final WalletType type;
    private final String fullName;
    private final String document;
    private final String email;
    private Money balance;

    private Wallet(Long id, WalletType type, String fullName, String document, String email, Money balance) {
        this.id = id;
        this.type = type;
        this.fullName = fullName;
        this.document = document;
        this.email = email;
        this.balance = balance;
    }

    public static Wallet create(WalletType type, String fullName, String document, String email) {
        if (type == null)
            throw new WalletDomainException("Wallet type is required");
        if (StringUtils.isEmpty(fullName))
            throw new WalletDomainException("Wallet name is required");
        if (StringUtils.isEmpty(document))
            throw new WalletDomainException("Document is required");
        if (StringUtils.isEmpty(email))
            throw new WalletDomainException("Email is required");
        return new Wallet(null, type, fullName, document, email, Money.ZERO);
    }

    public static Wallet restore(Long id, WalletType type, String fullName, String document, String email, Money balance) {
        if (id == null)
            throw new WalletDomainException("Wallet ID cannot be null");
        if (type == null)
            throw new WalletDomainException("Wallet type is required");
        if (StringUtils.isEmpty(fullName))
            throw new WalletDomainException("Wallet name is required");
        if (StringUtils.isEmpty(document))
            throw new WalletDomainException("Document is required");
        if (StringUtils.isEmpty(email))
            throw new WalletDomainException("Email is required");
        return new Wallet(id, type, fullName, document, email, balance);
    }

    public void debit(Money amount) {
        this.balance = this.balance.subtract(amount);
    }

    public void credit(Money amount) {
        this.balance = this.balance.add(amount);
    }

    public Long getId() {
        return id;
    }

    public WalletType getType() {
        return type;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public Money getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet that = (Wallet) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
