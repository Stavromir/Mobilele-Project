package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.validation.FieldMatch;
import bg.softuni.mobilele.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Password should match"
)
public record UserRegistrationDto(@NotEmpty String firstName,
                                  @NotEmpty String lastName,
                                  @NotNull @Email @UniqueUserEmail String email,
                                  String password,
                                  String confirmPassword) {
}
