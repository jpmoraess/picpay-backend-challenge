package br.com.jpmoraess.picpay_backend_challenge.api;

import br.com.jpmoraess.picpay_backend_challenge.api.request.TransferRequest;
import br.com.jpmoraess.picpay_backend_challenge.application.usecase.TransferBetweenWallets;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transfers")
public class TransferApi {

    private final TransferBetweenWallets transferBetweenWallets;

    public TransferApi(TransferBetweenWallets transferBetweenWallets) {
        this.transferBetweenWallets = transferBetweenWallets;
    }

    @PostMapping
    public void execute(@Valid @RequestBody TransferRequest request) {
        TransferBetweenWallets.TransferInput input = TransferBetweenWallets.TransferInput
                .of(request.payerId(), request.payeeId(), request.amount());
        TransferBetweenWallets.TransferOutput output = transferBetweenWallets.execute(input);
    }
}
