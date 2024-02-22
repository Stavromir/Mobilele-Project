package bg.softuni.mobilele.model.dto;

public record UserRegistrationDto(String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  String confirmPassword) {
}
