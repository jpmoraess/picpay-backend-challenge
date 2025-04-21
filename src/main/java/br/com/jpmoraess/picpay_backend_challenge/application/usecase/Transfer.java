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

        Money amount = new Money(input.amount());

        if (!payer.getBalance().isGreaterThanZero() || !payer.getBalance().isGreaterThan(amount))
            throw new WalletDomainException("Insufficient funds");

        Wallet payee = walletRepository.getById(input.payeeId())
                .orElseThrow(() -> new WalletNotFoundException("Destination wallet not found"));

        payer.debit(amount);
        walletRepository.updateBalance(payer.getId(), payer.getBalance().getAmount());

        payee.credit(amount);
        walletRepository.updateBalance(payee.getId(), payee.getBalance().getAmount());

        Transaction transaction = Transaction.create(payer.getId(), payee.getId(), amount);
        transactionRepository.save(transaction);
    }


    public record TransferInput(Long payerId, Long payeeId, BigDecimal amount) {
        public static TransferInput of(Long payerId, Long payeeId, BigDecimal amount) {
            return new TransferInput(payerId, payeeId, amount);
        }
    }
}
