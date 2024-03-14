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


    public UserEntity createTestUser(String email) {
        return createUser(email, List.of(UserRoleEnum.USER));
    }

    public UserEntity createTestAdmin(String email) {
        return createUser(email, List.of(UserRoleEnum.ADMIN));
    }

    private void userRolesInit() {
        userRoleRepository.saveAll(List.of(
                new UserRoleEntity().setRole(UserRoleEnum.USER),
                new UserRoleEntity().setRole(UserRoleEnum.ADMIN)
        ));
    }

    private UserEntity createUser(String email, List<UserRoleEnum> roles) {

        userRolesInit();

        List<UserRoleEntity> allByRoleIn = userRoleRepository.findAllByRoleIn(roles);


        UserEntity newUser = new UserEntity()
                .setActive(true)
                .setEmail(email)
                .setPassword("test")
                .setFirstName("Test user first")
                .setLastName("Test user last")
                .setRoles(allByRoleIn);

        return userRepository.save(newUser);
    }

    public void cleanUp() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }
}
