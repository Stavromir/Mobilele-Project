package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.events.UserRegisteredEvent;
import bg.softuni.mobilele.service.EmailService;
import bg.softuni.mobilele.service.UserActivationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private final EmailService emailService;

    public UserActivationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    @EventListener(UserRegisteredEvent.class)
    public void userRegistered(UserRegisteredEvent event) {

        //TODO: Add activation links

        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUserName());

    }

    @Override
    public void cleanUpObsoleteActivationLink() {
        //todo Implement
    }
}
