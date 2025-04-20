package br.com.jpmoraess.picpay_backend_challenge.application.repository;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository {

    Wallet save(Wallet wallet);

    Optional<Wallet> getById(UUID id);
}
