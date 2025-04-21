package br.com.jpmoraess.picpay_backend_challenge.application.repository;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;

import java.util.Optional;

public interface WalletRepository {

    Wallet save(Wallet wallet);

    Optional<Wallet> findForUpdate(Long id);

    Optional<Wallet> getById(Long id);
}
