package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.transfer;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.TransferRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferRepositoryAdapter implements TransferRepository {

    private final TransferDataMapper transferDataMapper;
    private final TransferJpaRepository transferJpaRepository;

    public TransferRepositoryAdapter(TransferDataMapper transferDataMapper, TransferJpaRepository transferJpaRepository) {
        this.transferDataMapper = transferDataMapper;
        this.transferJpaRepository = transferJpaRepository;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferDataMapper.fromEntity(transferJpaRepository.save(transferDataMapper.fromDomain(transfer)));
    }
}
