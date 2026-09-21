package mzn.faisal.authservice.business.dto.auth.register;

public record RegisterResponse(
        String accessToken,
        String refreshToken,
        String partyName
) {
}
