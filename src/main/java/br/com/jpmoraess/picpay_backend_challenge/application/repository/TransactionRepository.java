package br.com.jpmoraess.picpay_backend_challenge.application.repository;

import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transaction;

public interface TransactionRepository {

    Transaction save(Transaction transaction);
}
