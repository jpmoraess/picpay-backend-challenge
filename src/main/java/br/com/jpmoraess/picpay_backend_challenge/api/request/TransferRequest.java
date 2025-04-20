package br.com.jpmoraess.picpay_backend_challenge.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(
        @NotNull(message = "Payer cannot be null")
        UUID payerId,

        @NotNull(message = "Payee cannot be null")
        UUID payeeId,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Must be a valid amount")
        BigDecimal amount
) {
}
