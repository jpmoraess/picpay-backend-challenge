package br.com.jpmoraess.picpay_backend_challenge.application.usecase;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.TransferRepository;
import br.com.jpmoraess.picpay_backend_challenge.application.repository.WalletRepository;
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
    private final TransferRepository transferRepository;

    public Transfer(WalletRepository walletRepository, TransferRepository transferRepository) {
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
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

        br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transfer transfer = br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transfer.create(payer.getId(), payee.getId(), amount);
        transferRepository.save(transfer);
    }


    public record TransferInput(Long payerId, Long payeeId, BigDecimal amount) {
        public static TransferInput of(Long payerId, Long payeeId, BigDecimal amount) {
            return new TransferInput(payerId, payeeId, amount);
        }
    }
}
