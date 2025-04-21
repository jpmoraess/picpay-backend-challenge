package br.com.jpmoraess.picpay_backend_challenge.application.usecase;

import br.com.jpmoraess.picpay_backend_challenge.application.repository.WalletRepository;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.Wallet;
import br.com.jpmoraess.picpay_backend_challenge.domain.entity.WalletType;
import org.springframework.stereotype.Component;

@Component
public class CreateWallet {

    private final WalletRepository walletRepository;

    public CreateWallet(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public CreateWalletOutput execute(CreateWalletInput input) {
        Wallet wallet = Wallet.create(input.type(), input.fullName(), input.document(), input.email());
        wallet = walletRepository.save(wallet);
        return CreateWalletOutput.of(wallet.getId(), wallet.getFullName());
    }

    public record CreateWalletInput(WalletType type, String fullName, String document, String email) {
        public static CreateWalletInput of(WalletType type, String fullName, String document, String email) {
            return new CreateWalletInput(type, fullName, document, email);
        }
    }

    public record CreateWalletOutput(Long id, String fullName) {
        public static CreateWalletOutput of(Long id, String fullName) {
            return new CreateWalletOutput(id, fullName);
        }
    }
}
