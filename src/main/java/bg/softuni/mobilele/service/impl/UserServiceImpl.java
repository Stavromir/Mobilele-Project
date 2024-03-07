package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.UserRegistrationDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {

        userRepository.save(map(userRegistrationDto));
    }

    private UserEntity map (UserRegistrationDto userRegistrationDto) {
        return new UserEntity()
                .setActive(true)
                .setEmail(userRegistrationDto.email())
                .setFirstName(userRegistrationDto.firstName())
                .setLastName(userRegistrationDto.lastName())
                .setPassword(passwordEncoder.encode(userRegistrationDto.password()));
    }
}
