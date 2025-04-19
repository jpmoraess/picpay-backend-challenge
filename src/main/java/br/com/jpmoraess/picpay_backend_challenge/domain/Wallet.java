package br.com.jpmoraess.picpay_backend_challenge.domain;

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

    public static Wallet createWallet(WalletType type, String fullName, String document, String email) {
        if (type == null)
            throw new IllegalArgumentException("Type cannot be null");
        return new Wallet(UUID.randomUUID(), type, fullName, document, email, BigDecimal.ZERO);
    }

    public static Wallet restoreWallet(UUID id, WalletType type, String fullName, String document, String email, BigDecimal balance) {
        // TODO: add validation to prevent data violation
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
