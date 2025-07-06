package fa.training.utils;

public class Validator {
    // Phone: 10 or 11 digits, starts with 0
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^0\\d{9,10}$");
    }

    // Order number: exactly 10 digits
    public static boolean isValidOrderNumber(String number) {
        return number != null && number.matches("^\\d{10}$");
    }
}
