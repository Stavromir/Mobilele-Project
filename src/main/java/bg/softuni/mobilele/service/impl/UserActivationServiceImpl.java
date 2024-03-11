package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.entity.UserActivationCodeEntity;
import bg.softuni.mobilele.model.events.UserRegisteredEvent;
import bg.softuni.mobilele.repository.UserActivationCoreRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.EmailService;
import bg.softuni.mobilele.service.UserActivationService;
import bg.softuni.mobilele.service.exception.ObjectNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private final static String ACTIVATION_CODE_SYMBOLS = "abcdfghijklmnoprqstuvwxyz1234567890";
    private final static int ACTIVATION_CODE_LENGTH = 20;

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserActivationCoreRepository userActivationCoreRepository;

    public UserActivationServiceImpl(EmailService emailService,
                                     UserRepository userRepository,
                                     UserActivationCoreRepository userActivationCoreRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userActivationCoreRepository = userActivationCoreRepository;
    }


    @Override
    @EventListener(UserRegisteredEvent.class)
    public void userRegistered(UserRegisteredEvent event) {

        //TODO: Add activation links

        String activationCode = createActivationCode(event.getUserEmail());

        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUserName(), activationCode);

    }

    @Override
    public void cleanUpObsoleteActivationLink() {
//        System.out.println("NOT YET");
        //todo Implement
    }

    @Override
    public String createActivationCode(String userEmail) {

        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();
        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUser(userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ObjectNotFoundException("User not found")));

        userActivationCoreRepository.save(userActivationCodeEntity);

        return activationCode;
    }

    private static String generateActivationCode() {
        Random random = new SecureRandom();
        StringBuilder activationCode = new StringBuilder();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {

            int randInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randInx));
        }

        return activationCode.toString();
    }
}
