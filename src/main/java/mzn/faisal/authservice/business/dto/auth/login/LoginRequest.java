package mzn.faisal.authservice.business.dto.auth.login;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @NotBlank(message = "identityIsRequired")
        String identityValue,

        @NotBlank(message = "passwordIsRequired")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_]).{8,}$",
                message = "invalidPasswordFormat")
        String password
) {
}
