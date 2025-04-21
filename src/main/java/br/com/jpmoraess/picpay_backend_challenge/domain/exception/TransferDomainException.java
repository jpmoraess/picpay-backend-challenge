package br.com.jpmoraess.picpay_backend_challenge.domain.exception;

public class TransferDomainException extends DomainException {

    public TransferDomainException(String message) {
        super(message);
    }

    public TransferDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
