package br.com.jpmoraess.picpay_backend_challenge.application.repository;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;

public interface WalletRepository {

    Wallet save(Wallet wallet);
}
