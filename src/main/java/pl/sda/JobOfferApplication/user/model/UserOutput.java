package pl.sda.JobOfferApplication.user.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserOutput {

    private Long id;
    private String name;
    private String login;
    private LocalDate creationDate;

    public UserOutput(Long id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
    }
}
