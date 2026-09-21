package mzn.faisal.authservice.business.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "refreshTokenRequired")
        String refreshToken
) {}