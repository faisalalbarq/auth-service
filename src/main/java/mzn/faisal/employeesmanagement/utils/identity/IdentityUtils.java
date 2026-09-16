package mzn.faisal.employeesmanagement.utils.identity;

public class IdentityUtils {

    public static String validateAndFormat(String userIdentity) {
        if (userIdentity == null || userIdentity.isBlank()) {
            return "";
        }

        String input = userIdentity.trim();

        if (input.contains("@")) {
            String formattedEmail = EmailUtils.formatEmail(input);
            return formattedEmail.isBlank() ? "" : formattedEmail;
        } else {
            String formattedPhone = PhoneUtils.formatPhoneNumber(input, null);
            return formattedPhone.isBlank() ? "" : formattedPhone;
        }
    }
}
