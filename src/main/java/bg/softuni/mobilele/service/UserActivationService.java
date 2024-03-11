package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.events.UserRegisteredEvent;

public interface UserActivationService {

    void userRegistered(UserRegisteredEvent event);

    void cleanUpObsoleteActivationLink();

    String createActivationCode(String userEmail);
}
