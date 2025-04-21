package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.wallet;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;

public class WalletDataMapper {

    private WalletDataMapper() {

    }

    public static WalletEntity fromDomain(Wallet wallet) {
        return new WalletEntity(
                wallet.getId(),
                wallet.getType(),
                wallet.getFullName(),
                wallet.getDocument(),
                wallet.getEmail(),
                wallet.getBalance().getAmount()
        );
    }

    public static Wallet fromEntity(WalletEntity entity) {
        return Wallet.restore(
                entity.getId(),
                entity.getType(),
                entity.getFullName(),
                entity.getDocument(),
                entity.getEmail(),
                new Money(entity.getBalance())
        );
    }
}
