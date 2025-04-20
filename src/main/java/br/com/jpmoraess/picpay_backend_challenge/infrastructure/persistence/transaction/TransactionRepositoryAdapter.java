package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.transaction;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.TransactionRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionDataMapper transactionDataMapper;
    private final TransactionJpaRepository transactionJpaRepository;

    public TransactionRepositoryAdapter(TransactionDataMapper transactionDataMapper, TransactionJpaRepository transactionJpaRepository) {
        this.transactionDataMapper = transactionDataMapper;
        this.transactionJpaRepository = transactionJpaRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionDataMapper.fromEntity(transactionJpaRepository.save(transactionDataMapper.fromDomain(transaction)));
    }
}
