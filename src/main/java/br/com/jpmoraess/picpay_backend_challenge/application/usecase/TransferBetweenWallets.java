package br.com.jpmoraess.picpay_backend_challenge.application.usecase;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.EntryRepository;
import br.com.jpmoraess.picpay_backend_challenge.application.repository.TransferRepository;
import br.com.jpmoraess.picpay_backend_challenge.application.repository.WalletRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Entry;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transfer;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;
import br.com.jpmoraess.picpay_backend_challenge.domain.exception.TransferDomainException;
import br.com.jpmoraess.picpay_backend_challenge.domain.exception.WalletNotFoundException;
import br.com.jpmoraess.picpay_backend_challenge.domain.vo.Money;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class TransferBetweenWallets {

    private final EntryRepository entryRepository;
    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;

    public TransferBetweenWallets(EntryRepository entryRepository, WalletRepository walletRepository, TransferRepository transferRepository) {
        this.entryRepository = entryRepository;
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public TransferOutput execute(TransferInput input) {
        validateInput(input);
        Wallet payer, payee;
        if (input.payerId() < input.payeeId()) {
            payer = lockWallet(input.payerId());
            payee = lockWallet(input.payeeId());
        } else {
            payee = lockWallet(input.payeeId());
            payer = lockWallet(input.payerId());
        }
        Money amount = new Money(input.amount());
        validatePayerBalance(payer, amount);
        Transfer transfer = createAndSaveTransfer(input, amount);
        createAndSaveEntry(input.payerId(), amount.negate());
        createAndSaveEntry(input.payeeId(), amount);
        updateWalletBalances(payer, payee, amount);
        return TransferOutput.of(transfer, payer, payee);
    }

    private void validateInput(TransferInput input) {
        if (input.payerId().equals(input.payeeId()))
            throw new TransferDomainException("Cannot transfer to your own wallet");
    }

    private void validatePayerBalance(Wallet payer, Money amount) {
        if (!payer.getBalance().isGreaterThanOrEqual(amount))
            throw new TransferDomainException("Payer does not have funds to transact");
    }

    private Wallet lockWallet(Long walletId) {
        return walletRepository.findForUpdate(walletId)
                .orElseThrow(() -> new WalletNotFoundException(String.format("Wallet with id %d not found", walletId)));
    }

    private Transfer createAndSaveTransfer(TransferInput input, Money amount) {
        Transfer transfer = Transfer.create(input.payerId(), input.payeeId(), amount);
        return transferRepository.save(transfer);
    }

    private void createAndSaveEntry(Long walletId, Money amount) {
        Entry entry = Entry.create(walletId, amount);
        entryRepository.save(entry);
    }

    private void updateWalletBalances(Wallet payer, Wallet payee, Money amount) {
        payer.debit(amount);
        payee.credit(amount);
        walletRepository.save(payer);
        walletRepository.save(payee);
    }

    public record TransferInput(Long payerId, Long payeeId, BigDecimal amount) {
        public static TransferInput of(Long payerId, Long payeeId, BigDecimal amount) {
            return new TransferInput(payerId, payeeId, amount);
        }
    }

    public record TransferOutput(Transfer transfer, Wallet payer, Wallet payee) {
        public static TransferOutput of(Transfer transfer, Wallet payer, Wallet payee) {
            return new TransferOutput(transfer, payer, payee);
        }
    }
}
