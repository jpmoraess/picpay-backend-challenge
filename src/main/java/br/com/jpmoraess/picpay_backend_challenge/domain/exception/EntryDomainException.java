package br.com.jpmoraess.picpay_backend_challenge.domain.exception;

public class EntryDomainException extends DomainException {
    public EntryDomainException(String message) {
        super(message);
    }

    public EntryDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
