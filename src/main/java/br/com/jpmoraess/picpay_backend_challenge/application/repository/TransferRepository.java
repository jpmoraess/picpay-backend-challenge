package br.com.jpmoraess.picpay_backend_challenge.application.repository;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transfer;

public interface TransferRepository {

    Transfer save(Transfer transfer);
}
