package pl.sda.JobOfferApplication.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.sda.JobOfferApplication.user.model.UserOutput;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String login;
    @JsonIgnore
    private final  LocalDate creationDate= LocalDate.now();
    private String password;


        public UserEntity(String name, String login, String password) {
        this.name = name;
        this.login = login;
       // this.creationDate = creationDate;
        this.password = password;
    }

    public UserOutput toOutput() {
        return new UserOutput(id, name, login, creationDate);
    }
}
