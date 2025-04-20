package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.wallet;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.WalletRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Component
public class WalletRepositoryAdapter implements WalletRepository {

    private final WalletDataMapper walletDataMapper;
    private final WalletJpaRepository walletJpaRepository;

    public WalletRepositoryAdapter(WalletDataMapper walletDataMapper, WalletJpaRepository walletJpaRepository) {
        this.walletDataMapper = walletDataMapper;
        this.walletJpaRepository = walletJpaRepository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity entity = walletDataMapper.fromDomain(wallet);
        entity = walletJpaRepository.save(entity);
        return walletDataMapper.fromEntity(entity);
    }

    @Override
    public void updateBalance(UUID id, BigDecimal newBalance) {
        walletJpaRepository.updateBalance(id, newBalance);
    }

    @Override
    public Optional<Wallet> getById(UUID id) {
        return walletJpaRepository.findById(id)
                .map(walletDataMapper::fromEntity);
    }
}
