package mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register;

public record RegisterRequest(
        String  name,
        String  identityValue,
        Integer identityTypeId,
        String  password
) {
}
