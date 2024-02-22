package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.UserRegistrationDto;
import bg.softuni.mobilele.model.entity.User;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {

        userRepository.save(map(userRegistrationDto));

    }

    private User map (UserRegistrationDto userRegistrationDto) {
        return new User()
                .setActive(true)
                .setEmail(userRegistrationDto.email())
                .setFirstName(userRegistrationDto.firstName())
                .setLastName(userRegistrationDto.lastName())
                .setPassword(passwordEncoder.encode(userRegistrationDto.password()));
    }
}
