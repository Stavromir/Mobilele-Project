package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.validation.FieldMatch;
import bg.softuni.mobilele.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Password should match."
)
public record UserRegistrationDto(

        @NotEmpty (message = "First name is required.")
        String firstName,
        @NotEmpty (message = "Last name is required.")
        String lastName,
        @NotEmpty (message = "Email is required.")
        @Email (message = "Valid email is required.")
        @UniqueUserEmail
        String email,
        @NotEmpty (message = "Password is required.")
        String password,
        @NotEmpty (message = "Password confirm is required.")
        String confirmPassword) {

    public static UserRegistrationDto empty () {
        return  new UserRegistrationDto(null, null, null, null, null);
    }
}
