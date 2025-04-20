package br.com.jpmoraess.picpay_backend_challenge.api;

import br.com.jpmoraess.picpay_backend_challenge.api.request.TransferRequest;
import br.com.jpmoraess.picpay_backend_challenge.application.usecase.Transfer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transfers")
public class TransferApi {

    private final Transfer transfer;

    public TransferApi(Transfer transfer) {
        this.transfer = transfer;
    }

    @PostMapping
    public void execute(@Valid @RequestBody TransferRequest request) {
        Transfer.TransferInput input = Transfer.TransferInput.of(request.payerId(), request.payeeId(), request.amount());
        transfer.execute(input);
    }
}
