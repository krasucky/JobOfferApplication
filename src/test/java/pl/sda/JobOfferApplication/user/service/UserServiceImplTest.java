package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldCreateUserCorrectly() throws UserException {

        //given
        UserInput userInput = new UserInput("Jan", "Kowalski", "1234");
        //when
        userService.createUser(userInput);

        //then
        final List<UserEntity> all = userRepository.findAll();
        assertEquals(1, all.size());
        final UserOutput userOutput = all.get(0).toOutput();
        userInput.equals(userOutput);
        assertEquals(userOutput.getLogin(), userInput.getLogin());
    }

    @Test
    public void shouldGetUserByIdCorrectly() throws UserException {

        //given
        UserEntity userEntity = new UserEntity("Jan", "Kowalski", "1234");
        userRepository.save(userEntity);
        Long id = userRepository.findAll().get(0).getId();

        //when
        UserOutput userById = userService.getUserById(id);

        //then
        assertFalse((userById == null));
        UserOutput userOutput = userEntity.toOutput();
        assertEquals(userOutput.getName(), userById.getName());
        assertEquals(userOutput.getLogin(), userById.getLogin());
    }

    @Test
    public void deleteUserTest() throws UserException {

        //given
        UserInput userInput = UserInput.builder()
                .name("abc")
                .login("abc")
                .password("admin1")
                .build();

        userService.createUser(userInput);

        //when
        Long id = userRepository.findAll().get(0).getId();
        userService.deleteUserById(id);

        //then
        final List<UserEntity> all = userRepository.findAll();
        assertTrue(all.size() == 0);
    }
}