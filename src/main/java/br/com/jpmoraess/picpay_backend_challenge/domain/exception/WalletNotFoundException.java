package br.com.jpmoraess.picpay_backend_challenge.domain.exception;

public class WalletNotFoundException extends DomainException {

    public WalletNotFoundException(String message) {
        super(message);
    }

    public WalletNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
