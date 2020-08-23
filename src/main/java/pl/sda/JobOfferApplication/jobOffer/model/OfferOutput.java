package pl.sda.JobOfferApplication.jobOffer.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.sda.JobOfferApplication.user.entity.UserEntity;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter

public class OfferOutput {
    private long id;
    private String category;
    private final LocalDate createDate = LocalDate.now();
    private LocalDate endDate = LocalDate.now().plusDays(30L);
    private String content;
    UserEntity userEntity;

    public OfferOutput(long id, String category, String content, UserEntity userEntity) {
        this.id = id;
        this.category = category;
        this.content = content;
        this.userEntity = userEntity;
    }
}
