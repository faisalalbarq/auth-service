package mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
public record RegisterRequest(
        @NotBlank(message = "nameIsRequired")
        String  name,

        @NotBlank(message = "identityIsRequired")
        String  identityValue,
        Integer identityTypeId,

        @NotBlank(message = "passwordIsRequired")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_]).{8,}$",
                message = "invalidPasswordFormat")
        String  password
) {
}
