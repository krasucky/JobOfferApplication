package pl.sda.JobOfferApplication.user.service;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.JobOfferApplication.user.entity.UserEntity;
import pl.sda.JobOfferApplication.user.exception.UserException;
import pl.sda.JobOfferApplication.user.model.UserInput;
import pl.sda.JobOfferApplication.user.model.UserOutput;
import pl.sda.JobOfferApplication.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final String NO_USER_FOUND_OR_GIVEN_ID = "No user found or given ID";
    public static final String THERE_IS_ALREADY_AN_USER_WITH_THIS_USER_NAME = "There is already an user with this userName";
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserInput userInput) throws UserException {

        validateUserInput(userInput);

        final String encode = passwordEncoder.encode(userInput.getPassword());

        UserEntity userEntity =
                new UserEntity(userInput.getName(),
                        userInput.getLogin(),
                        userInput.getCreationDate(),
                        encode);

        userRepository.save(userEntity);
    }

    @Override
    public List<UserOutput> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::toOutput)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutput getUserById(Long id) throws UserException {

       final Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
       if(!optionalUserEntity.isPresent()) {
           throw new UserException(NO_USER_FOUND_OR_GIVEN_ID);
       }
       return optionalUserEntity.get().toOutput();
    }

    private void validateUserInput(UserInput userInput) throws UserException {
        if(userRepository.existsByLogin(userInput.getLogin())) {
            throw new UserException(THERE_IS_ALREADY_AN_USER_WITH_THIS_USER_NAME);
        }
    }
}
