package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {

    @Modifying
    @Query("UPDATE WalletEntity w SET w.balance = :newBalance WHERE w.id = :id")
    void updateBalance(@Param("id") Long id, @Param("newBalance") BigDecimal newBalance);
}
