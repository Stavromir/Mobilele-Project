package bg.softuni.mobilele.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordLoginValidator implements ConstraintValidator<PasswordLogin, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
