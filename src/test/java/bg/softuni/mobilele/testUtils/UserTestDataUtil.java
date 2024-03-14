package bg.softuni.mobilele.testUtils;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    public UserEntity createTestUser() {
        return createUser(List.of(UserRoleEnum.USER));
    }

    public UserEntity createTestAdmin() {
        return createUser(List.of(UserRoleEnum.ADMIN));
    }

    private UserEntity createUser(List<UserRoleEnum> roles){

        List<UserRoleEntity> allByRoleIn = userRoleRepository.findAllByRoleIn(roles);


        UserEntity newUser = new UserEntity()
                .setActive(true)
                .setEmail("test@email.com")
                .setFirstName("Test user first")
                .setLastName("Test user last")
                .setRoles(allByRoleIn);

        return userRepository.save(newUser);
    }

    public String createTestOffer(UserEntity owner) {

    }

    public void cleanUp() {
        userRepository.deleteAll();
    }
}
