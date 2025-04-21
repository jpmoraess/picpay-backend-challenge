package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.transfer;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.TransferRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferRepositoryAdapter implements TransferRepository {

    private final TransferJpaRepository transferJpaRepository;

    public TransferRepositoryAdapter(TransferJpaRepository transferJpaRepository) {
        this.transferJpaRepository = transferJpaRepository;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return TransferDataMapper.fromEntity(transferJpaRepository.save(TransferDataMapper.fromDomain(transfer)));
    }
}
