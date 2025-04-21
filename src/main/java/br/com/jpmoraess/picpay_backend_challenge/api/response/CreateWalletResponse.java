package br.com.jpmoraess.picpay_backend_challenge.api.response;

public record CreateWalletResponse(Long id, String message) {
    public static CreateWalletResponse of(Long id, String message) {
        return new CreateWalletResponse(id, message);
    }
}
