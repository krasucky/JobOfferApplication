package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;
import pl.sda.JobOfferApplication.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldCreateUserCorrectly() throws UserException {

        //given
        UserInput userInput = new UserInput("Jan", "Kowalski", "1234");

        //when
        userService.createUser(userInput);

        //then
        final List<UserEntity> all = userRepository.findAll();
        assertTrue(all.size() == 1);
        final UserOutput userOutput = all.get(0).toOutput();

        userInput.equals(userOutput);
        assertEquals(userOutput.getLogin(), userInput.getLogin());
    }

}