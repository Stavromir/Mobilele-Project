package bg.softuni.mobilele.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PasswordLoginValidator.class)
public @interface PasswordLogin {

    String message () default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
