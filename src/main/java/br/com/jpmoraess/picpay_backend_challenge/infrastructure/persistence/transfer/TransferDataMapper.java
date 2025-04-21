package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.transfer;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transfer;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;
import org.springframework.stereotype.Component;

@Component
public class TransferDataMapper {

    public TransferEntity fromDomain(Transfer transfer) {
        return new TransferEntity(
                transfer.getId(),
                transfer.getPayerId(),
                transfer.getPayeeId(),
                transfer.getAmount().getAmount(),
                transfer.getDateTime()
        );
    }

    public Transfer fromEntity(TransferEntity entity) {
        return Transfer.restore(
                entity.getId(),
                entity.getPayerId(),
                entity.getPayeeId(),
                new Money(entity.getAmount()),
                entity.getDateTime()
        );
    }
}
