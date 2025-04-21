package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.entry;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.EntryRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Entry;
import org.springframework.stereotype.Component;

@Component
public class EntryRepositoryAdapter implements EntryRepository {

    private final EntryJpaRepository entryJpaRepository;

    public EntryRepositoryAdapter(EntryJpaRepository entryJpaRepository) {
        this.entryJpaRepository = entryJpaRepository;
    }

    @Override
    public void save(Entry entry) {
        EntryDataMapper.fromEntity(entryJpaRepository.save(EntryDataMapper.fromDomain(entry)));
    }
}
