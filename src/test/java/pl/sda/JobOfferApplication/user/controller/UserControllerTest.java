package pl.sda.JobOfferApplication.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.sda.JobOfferApplication.JobOfferApplication;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.repository.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sda.JobOfferApplication.user.controller.UserController.USERS_MAPPING;


@AutoConfigureMockMvc
@SpringBootTest(classes = JobOfferApplication.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    public void shouldCreateUserProperly() throws Exception {
        UserInput userInput = UserInput.builder()
                .name("sadasd")
                .login("Awdawdaw")
                .password("ddaedawndai")
                .build();

        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(USERS_MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(userInput));

        // When
        final ResultActions resultActions = mockMvc.perform(requestBuilder);
        // Then
        resultActions.andExpect(status().isCreated());
    }

    public static String toJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}