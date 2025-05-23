package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.wallet;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.WalletType;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "wallets")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletType type;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "document", unique = true, nullable = false)
    private String document;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public WalletEntity(Long id, WalletType type, String fullName, String document, String email, BigDecimal balance, LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.fullName = fullName;
        this.document = document;
        this.email = email;
        this.balance = balance;
        this.updatedAt = updatedAt;
    }

    public WalletEntity(Long id, WalletType type, String fullName, String document, String email, BigDecimal balance) {
        this.id = id;
        this.type = type;
        this.fullName = fullName;
        this.document = document;
        this.email = email;
        this.balance = balance;
    }

    public WalletEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletType getType() {
        return type;
    }

    public void setType(WalletType type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletEntity that = (WalletEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
