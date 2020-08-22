package pl.sda.JobOfferApplication.user.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.repository.UserRepository;
import pl.sda.JobOfferApplication.user.service.UserService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInputTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void CreateUserCorrectly() throws UserException {

        //given
        UserInput userInput = new UserInput("abc", "abc", "admin1");

        // when
        userService.createUser(userInput);

        //then
        final List<UserEntity> all = userRepository.findAll();
        assertEquals(all.size(), 1);
        final UserOutput userOutput = all.get(0).toOutput();
        userInput.equals(userOutput);
        assertEquals(userOutput.getLogin(), userInput.getLogin());
    }

}