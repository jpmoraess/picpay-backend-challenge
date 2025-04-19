package br.com.jpmoraess.picpay_backend_challenge.api;

import br.com.jpmoraess.picpay_backend_challenge.api.mapper.WalletMapper;
import br.com.jpmoraess.picpay_backend_challenge.api.request.CreateWalletRequest;
import br.com.jpmoraess.picpay_backend_challenge.api.response.CreateWalletResponse;
import br.com.jpmoraess.picpay_backend_challenge.application.usecase.CreateWallet;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/wallets")
public class WalletApi {

    private final WalletMapper walletMapper;
    private final CreateWallet createWallet;

    public WalletApi(WalletMapper walletMapper, CreateWallet createWallet) {
        this.walletMapper = walletMapper;
        this.createWallet = createWallet;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateWalletResponse createWallet(@Valid @RequestBody CreateWalletRequest request) {
        CreateWallet.CreateWalletInput input = walletMapper.fromRequest(request);
        CreateWallet.CreateWalletOutput output = createWallet.execute(input);
        return walletMapper.fromOutput(output);
    }
}
