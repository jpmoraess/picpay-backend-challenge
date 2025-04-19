package br.com.jpmoraess.picpay_backend_challenge.api.mapper;

import br.com.jpmoraess.picpay_backend_challenge.api.request.CreateWalletRequest;
import br.com.jpmoraess.picpay_backend_challenge.api.response.CreateWalletResponse;
import br.com.jpmoraess.picpay_backend_challenge.application.usecase.CreateWallet;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public CreateWalletResponse fromOutput(CreateWallet.CreateWalletOutput output) {
        return CreateWalletResponse.of(output.id(), "Welcome ".concat(output.fullName()));
    }

    public CreateWallet.CreateWalletInput fromRequest(CreateWalletRequest request) {
        return CreateWallet.CreateWalletInput.of(request.type(), request.fullName(), request.document(), request.email());
    }
}
