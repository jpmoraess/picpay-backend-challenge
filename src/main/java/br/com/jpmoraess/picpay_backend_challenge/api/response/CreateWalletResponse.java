package br.com.jpmoraess.picpay_backend_challenge.api.response;

import java.util.UUID;

public record CreateWalletResponse(UUID id, String message) {
    public static CreateWalletResponse of(UUID id, String message) {
        return new CreateWalletResponse(id, message);
    }
}
