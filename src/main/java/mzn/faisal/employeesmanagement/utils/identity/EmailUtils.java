package mzn.faisal.employeesmanagement.utils.identity;

import java.util.regex.Pattern;

public class EmailUtils {
    private final static String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email){
        if (email == null || email.isBlank()){
            return false;
        }

        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static String formatEmail(String email){
        if (!isValidEmail(email)) return "";

        return email.trim().toLowerCase();
    }
}
