package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.model.dto.UserRegistrationDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {

        userRepository.save(map(userRegistrationDto));

    }

    @Override
    public boolean loginUser(UserLoginDto userLoginDto) {

        var user = userRepository.findByEmail(userLoginDto.email())
                .orElse(null);

        boolean loginSuccess = false;

        if (user != null) {

            String encodedPassword = user.getPassword();
            String rowPassword = userLoginDto.password();

            loginSuccess = encodedPassword != null &&
                    passwordEncoder.matches(rowPassword, encodedPassword);

            if (loginSuccess) {
                currentUser
                        .setLogged(true)
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName());
            } else {
                currentUser.logout();
            }
        }
        return loginSuccess;
    }

    @Override
    public void logoutUser() {
        currentUser.logout();
    }

    private UserEntity map (UserRegistrationDto userRegistrationDto) {
        return new UserEntity()
                .setActive(true)
                .setEmail(userRegistrationDto.email())
                .setFirstName(userRegistrationDto.firstName())
                .setLastName(userRegistrationDto.lastName())
                .setPassword(passwordEncoder.encode(userRegistrationDto.lastName()));
    }
}
