package br.com.jpmoraess.picpay_backend_challenge.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferRequest(
        @NotNull(message = "Payer cannot be null")
        Long payerId,

        @NotNull(message = "Payee cannot be null")
        Long payeeId,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Must be a valid amount")
        BigDecimal amount
) {
}
