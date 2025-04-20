package br.com.jpmoraess.picpay_backend_challenge.domain.exception;

public class TransactionDomainException extends DomainException {

    public TransactionDomainException(String message) {
        super(message);
    }

    public TransactionDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
