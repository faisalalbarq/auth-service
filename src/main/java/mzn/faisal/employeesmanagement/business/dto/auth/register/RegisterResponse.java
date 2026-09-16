package mzn.faisal.employeesmanagement.business.dto.auth.register;

public record RegisterResponse(
        String token,
        String partyName
) {
}
