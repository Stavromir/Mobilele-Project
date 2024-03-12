package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MobileleUserDetailServiceTest {

    private MobileleUserDetailService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new MobileleUserDetailService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {

//        when(mockUserRepository.findByEmail("pesho@abv.bg"))
//                .thenReturn(Optional.of(new UserEntity()));

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("pesho@abv.bg")
        );
    }

    @Test
    void testUserFound() {
        // Arrange
        UserEntity testUserEntity = createTestUser();

        when(mockUserRepository.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        // Act
        UserDetails userDetails = serviceToTest
                .loadUserByUsername(testUserEntity.getEmail());

        // Assert
        Assertions.assertNotNull(userDetails);

        Assertions.assertEquals(
                testUserEntity.getEmail(),
                userDetails.getUsername(),
                "Username is not mapped to email"
        );

        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());

        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN));
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER));
    }

    @Test
    void testMock() {

        UserEntity userEntity  = new UserEntity();
        userEntity.setFirstName("Jivko");

        when(mockUserRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(userEntity));


        Optional<UserEntity> userOpt = mockUserRepository
                .findByEmail("test@example.com");

        UserEntity user = userOpt.get();

        Assertions.assertEquals("Jivko", user.getFirstName());
    }


    private static UserEntity createTestUser () {
        return new UserEntity()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setPassword("test")
                .setEmail("ivan@abv.bg")
                .setActive(false)
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));
    }

    private static boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {

        return userDetails.getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }
}
