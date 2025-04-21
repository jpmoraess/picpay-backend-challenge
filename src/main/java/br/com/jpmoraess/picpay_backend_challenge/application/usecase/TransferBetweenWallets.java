package br.com.jpmoraess.picpay_backend_challenge.application.usecase;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.EntryRepository;
import br.com.jpmoraess.picpay_backend_challenge.application.repository.TransferRepository;
import br.com.jpmoraess.picpay_backend_challenge.application.repository.WalletRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Entry;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Transfer;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;
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
        Wallet payer, payee;
        if (input.payerId() < input.payeeId()) {
            payer = walletRepository.findForUpdate(input.payerId())
                    .orElseThrow(() -> new WalletNotFoundException("Payer wallet not found"));
            payee = walletRepository.findForUpdate(input.payeeId())
                    .orElseThrow(() -> new WalletNotFoundException("Payee wallet not found"));
        } else {
            payee = walletRepository.findForUpdate(input.payeeId())
                    .orElseThrow(() -> new WalletNotFoundException("Payee wallet not found"));
            payer = walletRepository.findForUpdate(input.payerId())
                    .orElseThrow(() -> new WalletNotFoundException("Payer wallet not found"));
        }

        Transfer transfer = Transfer.create(input.payerId(), input.payeeId(), new Money(input.amount()));
        transferRepository.save(transfer);

        Entry payerEntry = Entry.create(input.payerId(), new Money(input.amount().negate()));
        Entry payeeEntry = Entry.create(input.payeeId(), new Money(input.amount()));
        entryRepository.save(payerEntry);
        entryRepository.save(payeeEntry);

        payer.debit(new Money(input.amount()));
        payee.credit(new Money(input.amount()));
        walletRepository.save(payer);
        walletRepository.save(payee);

        return TransferOutput.of(transfer, payer, payee, payerEntry, payeeEntry);
    }


    public record TransferInput(Long payerId, Long payeeId, BigDecimal amount) {
        public static TransferInput of(Long payerId, Long payeeId, BigDecimal amount) {
            return new TransferInput(payerId, payeeId, amount);
        }
    }

    public record TransferOutput(Transfer transfer, Wallet payer, Wallet payee, Entry payerEntry, Entry payeeEntry) {
        public static TransferOutput of(Transfer transfer, Wallet payer, Wallet payee, Entry payerEntry, Entry payeeEntry) {
            return new TransferOutput(transfer, payer, payee, payerEntry, payeeEntry);
        }
    }
}
