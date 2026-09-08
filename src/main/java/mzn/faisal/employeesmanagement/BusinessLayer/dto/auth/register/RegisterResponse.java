package mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register;

public record RegisterResponse(
        String token,
        String partyName
) {
}
