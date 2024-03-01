package bg.softuni.mobilele.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserLoginDto(
        @Email
        @NotEmpty
        String email,
        @NotEmpty
        String password) {

    public static UserLoginDto empty () {
        return new UserLoginDto(null, null);
    }
}
