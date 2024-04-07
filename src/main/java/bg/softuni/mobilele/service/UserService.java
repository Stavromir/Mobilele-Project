package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserRegistrationDto;
import org.springframework.security.core.Authentication;

public interface UserService {

    void registerUser (UserRegistrationDto userRegistrationDto);

    void createUserIfNotExist(String email, String name);

    Authentication login (String email);

}
