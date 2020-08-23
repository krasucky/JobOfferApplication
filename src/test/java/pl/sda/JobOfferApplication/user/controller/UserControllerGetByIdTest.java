package pl.sda.JobOfferApplication.user.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sda.JobOfferApplication.JobOfferApplication;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.repository.UserRepository;
import pl.sda.JobOfferApplication.user.service.UserService;

import static pl.sda.JobOfferApplication.user.controller.UserController.USERS_MAPPING;

@AutoConfigureMockMvc
@SpringBootTest(classes = JobOfferApplication.class)
class UserControllerGetByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void getUserByIdProperly() throws Exception {

        //given
        UserInput userInput = UserInput.builder()
                .name("sadasd")
                .login("Awdawdaw")
                .password("ddaedawndai")
                .build();

        userService.createUser(userInput);
        Long id = userService.getAllUsers().get(0).getId();
        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(USERS_MAPPING + "/" + id)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        final ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}