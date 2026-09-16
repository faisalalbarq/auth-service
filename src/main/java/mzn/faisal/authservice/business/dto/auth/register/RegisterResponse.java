package mzn.faisal.authservice.business.dto.auth.register;

public record RegisterResponse(
        String token,
        String partyName
) {
}
