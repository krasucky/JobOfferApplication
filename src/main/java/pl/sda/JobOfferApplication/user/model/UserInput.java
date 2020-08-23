package pl.sda.JobOfferApplication.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class UserInput {

    private String name;
    private String login;
    @JsonIgnore
    private final LocalDate creationDate = LocalDate.now();
    private String password;

    public UserInput(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

}
