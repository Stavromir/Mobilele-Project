package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.model.dto.UserRegistrationDto;

public interface UserService {

    void registerUser (UserRegistrationDto userRegistrationDto);

}
