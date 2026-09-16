package mzn.faisal.employeesmanagement.utils.identity;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneUtils {
    private final static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static String formatPhoneNumber(String phoneNumber, String isoCode) {
        String region = (isoCode == null || isoCode.isBlank()) ? "JO" : isoCode.toUpperCase();

        try {
            PhoneNumber parsedPhoneNumber = phoneUtil.parse(phoneNumber, region);
            if (!phoneUtil.isValidNumber(parsedPhoneNumber)) {
                return "";
            }
            return phoneUtil.format(parsedPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);

        } catch (NumberParseException e) {
            return "";
        }
    }
}
