package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.transaction;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transaction;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;
import org.springframework.stereotype.Component;

@Component
public class TransactionDataMapper {

    public TransactionEntity fromDomain(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId(),
                transaction.getPayerId(),
                transaction.getPayeeId(),
                transaction.getAmount().getAmount(),
                transaction.getDateTime()
        );
    }

    public Transaction fromEntity(TransactionEntity entity) {
        return Transaction.restore(
                entity.getId(),
                entity.getPayerId(),
                entity.getPayeeId(),
                new Money(entity.getAmount()),
                entity.getDateTime()
        );
    }
}
