package br.com.jpmoraess.picpay_backend_challenge.api.request;

import br.com.jpmoraess.picpay_backend_challenge.domain.WalletType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CreateWalletRequest(
        @NotNull(message = "Type cannot be null")
        WalletType type,

        @NotEmpty(message = "Full name is required")
        @Size(min = 15, max = 35)
        String fullName,

        @CPF
        @NotEmpty(message = "Document is required")
        String document,

        @Email(message = "Must be a valid e-mail")
        @NotEmpty(message = "E-mail is required")
        String email
) {
}
