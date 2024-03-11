package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.events.UserRegisteredEvent;
import bg.softuni.mobilele.service.UserActivationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    @Override
    @EventListener(UserRegisteredEvent.class)
    public void userRegistered(UserRegisteredEvent event) {

        //TODO: Add activation links

        System.out.println("User with email " + event.getUserEmail());

    }
}
