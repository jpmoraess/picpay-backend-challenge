package br.com.jpmoraess.picpay_backend_challenge.application.repository;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;

import java.math.BigDecimal;
import java.util.Optional;

public interface WalletRepository {

    Wallet save(Wallet wallet);

    void updateBalance(Long id, BigDecimal newBalance);

    Optional<Wallet> getById(Long id);
}
