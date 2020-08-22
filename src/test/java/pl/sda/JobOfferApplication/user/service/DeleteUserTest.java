package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.JobOfferApplication;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;
import pl.sda.JobOfferApplication.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = JobOfferApplication.class)
class DeleteUserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }
    @Test
    public void shouldCreateUserCorrectly() throws UserException {
        //given
        UserInput userInput = new UserInput("asdasd", "dasdasd", "asdasdasdasd");
        UserInput userInput1 = new UserInput("asdasd", "dasdasd", "asdasdasdasd");

        //when
        userService.deleteUserById(1L);


        //then
        final List<UserEntity> all = userRepository.findAll();
        assertTrue(all.size() == 1);

        final UserOutput userOutput = all.get(0).toOutput();
        userInput.equals(userOutput);
        assertEquals(userOutput.getLogin(), userInput.getLogin());
    }


}