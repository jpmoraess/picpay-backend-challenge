package br.com.jpmoraess.picpay_backend_challenge.domain.exception;

public class WalletDomainException extends DomainException {

    public WalletDomainException(String message) {
        super(message);
    }

    public WalletDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
