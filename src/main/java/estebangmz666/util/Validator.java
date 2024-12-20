package estebangmz666.util;

import java.util.regex.Pattern;

public class Validator {
    
    public static Boolean validateInputs(String name, String cellphone){
        return name != null && cellphone != null;
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}