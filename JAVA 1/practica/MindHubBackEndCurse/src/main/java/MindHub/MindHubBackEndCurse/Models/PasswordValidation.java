package MindHub.MindHubBackEndCurse.Models;

import java.util.regex.Pattern;

public class PasswordValidation {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])" +         //al menos un dígito
                    "(?=.*[a-z])" +         //al menos una minúscula
                    "(?=.*[A-Z])" +         //al menos una mayúscula
                    "(?=\\S+$)" +           //sin espacios en blanco
                    ".{8,}" +               //al menos 8 caracteres
                    "$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean validate(final String password) {
        return pattern.matcher(password).matches();
    }
    //"(?=.*[@#$%^&+=])" +    //al menos un carácter especial
    //"(?!.*(.).*\\1)" +      // no caracteres repetidos consecutivos
}
