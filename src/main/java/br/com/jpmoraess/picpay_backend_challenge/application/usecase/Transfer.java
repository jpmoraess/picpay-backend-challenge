package br.com.jpmoraess.picpay_backend_challenge.application.usecase;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.TransactionRepository;
import br.com.jpmoraess.picpay_backend_challenge.application.repository.WalletRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transaction;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;
import br.com.jpmoraess.picpay_backend_challenge.domain.exception.WalletDomainException;
import br.com.jpmoraess.picpay_backend_challenge.domain.exception.WalletNotFoundException;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class Transfer {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public Transfer(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void execute(TransferInput input) {
        Wallet payer = walletRepository.getById(input.payerId())
                .orElseThrow(() -> new WalletNotFoundException("Source wallet not found"));

        Money value = new Money(input.amount());

        if (!payer.getBalance().isGreaterThanZero() || !payer.getBalance().isGreaterThan(value))
            throw new WalletDomainException("Insufficient funds");

        Wallet payee = walletRepository.getById(input.payeeId())
                .orElseThrow(() -> new WalletNotFoundException("Destination wallet not found"));

        payer.debit(value);
        payee.credit(value);
        walletRepository.save(payer);
        walletRepository.save(payee);

        Transaction transaction = Transaction.create(payer.getId(), payee.getId(), value, LocalDateTime.now());
        transactionRepository.save(transaction);
    }


    public record TransferInput(UUID payerId, UUID payeeId, BigDecimal amount) {
        public static TransferInput of(UUID payerId, UUID payeeId, BigDecimal amount) {
            return new TransferInput(payerId, payeeId, amount);
        }
    }
}
