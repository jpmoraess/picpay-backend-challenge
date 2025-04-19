package br.com.jpmoraess.picpay_backend_challenge.domain.entity;

import br.com.jpmoraess.picpay_backend_challenge.domain.exception.WalletDomainException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {

    private final UUID id;
    private final WalletType type;
    private final String fullName;
    private final String document;
    private final String email;
    private final BigDecimal balance;

    private Wallet(UUID id, WalletType type, String fullName, String document, String email, BigDecimal balance) {
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
        return new Wallet(UUID.randomUUID(), type, fullName, document, email, BigDecimal.ZERO);
    }

    public static Wallet restore(UUID id, WalletType type, String fullName, String document, String email, BigDecimal balance) {
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

    public UUID getId() {
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

    public BigDecimal getBalance() {
        return balance;
    }
}
