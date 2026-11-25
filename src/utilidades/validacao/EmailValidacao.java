
package utilidades.validacao;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidacao {

    public static boolean validarEmail(String email) {

        if (email == null || email.isBlank()) {
            return false;
        }

        return EmailValidator.getInstance().isValid(email);
    }
}
