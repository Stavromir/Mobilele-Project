package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.testUtils.TestDataUtil;
import bg.softuni.mobilele.testUtils.UserTestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTestIT {

    private static final String USER_ONE_EMAIL = "user1@email.com";
    private static final String USER_TWO_EMAIL = "user2@email.com";
    private static final String ADMIN_EMAIL = "admin@email.com";

    @Autowired
    private TestDataUtil testDataUtil;

    @Autowired
    private UserTestDataUtil userTestDataUtil;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        testDataUtil.cleanAllTestData();
        userTestDataUtil.cleanUp();
    }

    @AfterEach
    void tearDown() {
        testDataUtil.cleanAllTestData();
        userTestDataUtil.cleanUp();
    }

    @Test
    void testAnonymousDeletionFails() throws Exception {

        UserEntity owner = userTestDataUtil.createTestUser(USER_ONE_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/offer/{uuid}", offerEntity.getUuid())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/users/login"));
    }

    @Test
    @WithMockUser(
            username = USER_ONE_EMAIL
    )
    void testNonAdminUserOwnedOffer() throws Exception {

        UserEntity owner = userTestDataUtil.createTestUser(USER_ONE_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/offer/{uuid}", offerEntity.getUuid())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/offers/all"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/all"));
    }

    @Test
    @WithMockUser(
            username = USER_ONE_EMAIL
    )
    void testNonAdminUserNonOfferOwner() throws Exception {

        UserEntity owner = userTestDataUtil.createTestUser(USER_TWO_EMAIL);
        userTestDataUtil.createTestUser(USER_ONE_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/offer/{uuid}", offerEntity.getUuid())
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }


    @Test
    @WithMockUser(
            username = ADMIN_EMAIL,
            roles = {"USER", "ADMIN"}
    )
    void testAdminUserNotOwnedOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(USER_ONE_EMAIL);
        userTestDataUtil.createTestAdmin(ADMIN_EMAIL);
        OfferEntity offerEntity = testDataUtil.createTestOffer(owner);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/offer/{uuid}", offerEntity.getUuid())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/offers/all"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/offers/all"));
    }
}
