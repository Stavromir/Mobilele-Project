package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.UserRegistrationDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.events.UserRegisteredEvent;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {

        userRepository.save(map(userRegistrationDto));

        applicationEventPublisher.publishEvent(new UserRegisteredEvent(
                "userService",
                userRegistrationDto.email(),
                userRegistrationDto.firstName()
        ));
    }

    private UserEntity map (UserRegistrationDto userRegistrationDto) {
        return new UserEntity()
                .setActive(false)
                .setEmail(userRegistrationDto.email())
                .setFirstName(userRegistrationDto.firstName())
                .setLastName(userRegistrationDto.lastName())
                .setPassword(passwordEncoder.encode(userRegistrationDto.password()));
    }
}
