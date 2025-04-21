package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.wallet;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.WalletRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WalletRepositoryAdapter implements WalletRepository {

    private final WalletJpaRepository walletJpaRepository;

    public WalletRepositoryAdapter(WalletJpaRepository walletJpaRepository) {
        this.walletJpaRepository = walletJpaRepository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity entity = WalletDataMapper.fromDomain(wallet);
        entity = walletJpaRepository.save(entity);
        return WalletDataMapper.fromEntity(entity);
    }

    @Override
    public Optional<Wallet> findForUpdate(Long id) {
        return walletJpaRepository.findForUpdate(id)
                .map(WalletDataMapper::fromEntity);
    }

    @Override
    public Optional<Wallet> getById(Long id) {
        return walletJpaRepository.findById(id)
                .map(WalletDataMapper::fromEntity);
    }
}
