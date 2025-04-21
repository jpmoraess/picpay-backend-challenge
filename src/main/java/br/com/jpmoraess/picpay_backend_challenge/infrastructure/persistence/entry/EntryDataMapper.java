package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.entry;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Entry;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;

public class EntryDataMapper {

    private EntryDataMapper() {

    }

    public static EntryEntity fromDomain(Entry entry) {
        return new EntryEntity(entry.getId(), entry.getWalletId(), entry.getAmount().getAmount());
    }

    public static Entry fromEntity(EntryEntity entity) {
        return Entry.restore(entity.getId(), entity.getWalletId(), new Money(entity.getAmount()));
    }
}
