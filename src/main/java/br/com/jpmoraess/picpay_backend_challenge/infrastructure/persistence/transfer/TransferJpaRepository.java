package br.com.jpmoraess.picpay_backend_challenge.infrastructure.persistence.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferJpaRepository extends JpaRepository<TransferEntity, UUID> {
}
