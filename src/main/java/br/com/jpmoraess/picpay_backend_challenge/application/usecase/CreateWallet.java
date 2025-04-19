package br.com.jpmoraess.picpay_backend_challenge.application.usecase;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.WalletRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.Wallet;
import br.com.jpmoraess.picpay_backend_challenge.domain.WalletType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateWallet {

    private final WalletRepository walletRepository;

    public CreateWallet(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public CreateWalletOutput execute(CreateWalletInput input) {
        Wallet wallet = Wallet.createWallet(input.type(), input.fullName(), input.document(), input.email());
        walletRepository.save(wallet);
        return CreateWalletOutput.of(wallet.getId(), wallet.getFullName());
    }

    public record CreateWalletInput(WalletType type, String fullName, String document, String email) {
        public static CreateWalletInput of(WalletType type, String fullName, String document, String email) {
            return new CreateWalletInput(type, fullName, document, email);
        }
    }

    public record CreateWalletOutput(UUID id, String fullName) {
        public static CreateWalletOutput of(UUID id, String fullName) {
            return new CreateWalletOutput(id, fullName);
        }
    }
}
