package mzn.faisal.authservice.business.dto.auth.login;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
