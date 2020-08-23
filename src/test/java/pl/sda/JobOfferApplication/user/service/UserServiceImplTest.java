package pl.sda.JobOfferApplication.user.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;
import pl.sda.JobOfferApplication.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

//    @AfterEach
//    void tearDown() {
//        userRepository.deleteAll();
//    }

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

    @Test
    public void shouldGetUserByIdCorrectly() throws UserException{

        //given
        UserEntity userEntity = new UserEntity ("Jan", "Kowalski", LocalDate.now() , "1234");
        userRepository.save(userEntity);

        //when
        UserOutput userById = userService.getUserById(1L);

        //then
        assertTrue(!(userById == null));
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

        UserInput userInput1 = UserInput.builder()
                .name("abca")
                .login("abca")
                .password("aadmin1")
                .build();

        //when
        userService.createUser(userInput);
        userService.createUser(userInput1);
        userService.deleteUserById(2L);


        //then
        final List<UserEntity> all = userRepository.findAll();
        assertTrue(all.size() == 1);

        final UserOutput userOutput = all.get(0).toOutput();
        userInput.equals(userOutput);
        assertEquals(userOutput.getLogin(), userInput.getLogin());
    }
    
}